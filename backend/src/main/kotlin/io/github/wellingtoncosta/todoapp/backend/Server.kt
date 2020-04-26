package io.github.wellingtoncosta.todoapp.backend

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.Jetty

fun main() {
    embeddedServer(Jetty, 8080) {
        routing {
            get("/") {
                call.respondText("{ \"message\": \"Hello world!\" }", ContentType.Application.Json)
            }
        }
    }.start(wait = true)
}
