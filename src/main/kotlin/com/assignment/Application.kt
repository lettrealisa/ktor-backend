package com.assignment

import com.assignment.dao.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.assignment.plugins.*

/*fun main() {
    embeddedServer(Netty, port = 8080, host = "localhost", module = Application::module)
        .start(wait = true)
}*/

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    val password = environment.config.propertyOrNull("postgres.password")?.getString() ?: ""
    val audience = environment.config.propertyOrNull("jwt.audience")?.getString() ?: ""
    val issuer = environment.config.propertyOrNull("jwt.issuer")?.getString() ?: ""
    val secret = environment.config.propertyOrNull("jwt.secret")?.getString() ?: ""
    val realm = environment.config.propertyOrNull("jwt.realm")?.getString() ?: ""
    DatabaseFactory.init()
    configureSockets()
    configureSerialization()
    configureHTTP()
    configureSecurity()
    configureRouting(audience, issuer, secret)
}
