package com.assignment

import com.assignment.dao.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.assignment.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "localhost", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val password = environment.config.propertyOrNull("postgres.password")?.getString() ?: ""
    DatabaseFactory.init()
    configureSockets()
    configureSerialization()
    configureHTTP()
    //configureSecurity()
    configureRouting()
}
