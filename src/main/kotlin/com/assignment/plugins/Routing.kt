package com.assignment.plugins

import com.assignment.dao.DAOFacade
import com.assignment.dao.DAOFacadeImpl
import com.assignment.dto.AuthDTO
import com.assignment.dto.GlucoseDTO
import com.assignment.dto.JwtDTO
import com.assignment.dto.UserDTO
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.locations.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import java.util.*

fun Application.configureRouting(audience: String, issuer: String, secret: String) {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }
    install(Locations) {
    }

    routing {
        val dao: DAOFacade = DAOFacadeImpl()
        get("/") {
            call.respondText("Hello World!")
        }
        get<MyLocation> {
                call.respondText("Location: name=${it.name}, arg1=${it.arg1}, arg2=${it.arg2}")
            }
            // Register nested routes
            get<Type.Edit> {
                call.respondText("Inside $it")
            }
            get<Type.List> {
                call.respondText("Inside $it")
            }
        get("/users") {
            call.respond(dao.allUsers())
        }
        post("/users") {
            val user = call.receive<UserDTO>()
            dao.addNewUser(user)
            call.respond(user)
        }
        get("/pets") {
            call.respond(dao.allPets())
        }
        authenticate("auth-jwt") {
            get("/foods") {
                call.respond(dao.allFoods())
            }
        }
        get("/glucose") {
            call.respond(dao.allGlucoseData())
        }
        post("/glucose") {
            val glucose = call.receive<GlucoseDTO>()
            dao.addNewGlucoseData(glucose)
            call.respond(dao)
        }
        post("/addRoleToUser") {
            val userId = call.request.queryParameters["user"]!!.toInt()
            val roleId = call.request.queryParameters["role"]!!.toInt()
            dao.addRoleToUser(userId, roleId)
            call.respond("Пользователь $userId получает роль $roleId")
        }
        post("/login") {
            val user = call.receive<UserDTO>()

            val token = JWT.create()
                .withAudience(audience)
                .withIssuer(issuer)
                .withClaim("username", user.name)
                .withExpiresAt(
                    Date(System.currentTimeMillis().plus(60000)))
                .sign(Algorithm.HMAC256(secret))

            val refreshToken = JWT.create()
                .withAudience(audience)
                .withIssuer(issuer)
                .withClaim("username", user.name)
                .withExpiresAt(
                    Date(System.currentTimeMillis().plus(60000).times(10)))
                .sign(Algorithm.HMAC256(secret))

            call.sessions.set(JwtDTO(token = refreshToken))
            call.respond(AuthDTO(token, user))
        }
        post("/refresh") {
            val refreshToken = call.sessions.get<JwtDTO>()

            val name = JWT.decode(refreshToken!!.token).claims["username"]!!.asString()

            val user = dao.userByName(name)

            val token = JWT.create()
                .withAudience(audience)
                .withIssuer(issuer)
                .withClaim("username", user?.name)
                .withExpiresAt(
                    Date(System.currentTimeMillis().plus(60000)))
                .sign(Algorithm.HMAC256(secret))

            call.respond(AuthDTO(token, user!!))
        }
    }
}
@Location("/location/{name}")
class MyLocation(val name: String, val arg1: Int = 42, val arg2: String = "default")
@Location("/type/{name}") data class Type(val name: String) {
    @Location("/edit")
    data class Edit(val type: Type)

    @Location("/list/{page}")
    data class List(val type: Type, val page: Int)
}
