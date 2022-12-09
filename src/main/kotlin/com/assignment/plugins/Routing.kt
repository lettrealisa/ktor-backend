package com.assignment.plugins

import com.assignment.dao.DAOFacade
import com.assignment.models.User
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.locations.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import com.assignment.dao.DAOFacadeImpl
import com.assignment.dto.UserDTO

fun Application.configureRouting() {
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
            println(user)
            dao.addNewUser(user)
            call.respond(user)
        }
        get("/pets") {
            call.respond(dao.allPets())
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
