package io.github.wellingtoncosta.todoapp.backend.app

import io.github.wellingtoncosta.todoapp.backend.domain.InvalidTodoStatusException
import io.github.wellingtoncosta.todoapp.backend.domain.TodoNotFoundException
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.Compression
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.serialization.DefaultJsonConfiguration
import io.ktor.serialization.json
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.Jetty
import io.ktor.util.error
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("Server")

fun main(args: Array<String>) {
    setupDatabase()
    embeddedServer(Jetty, commandLineEnvironment(args)).start(wait = true)
}

@Serializable data class ExceptionResponse(val code: Int, val message: String, val timestamp: Long)

@Suppress("unused")
fun Application.main() {
    install(DefaultHeaders)
    install(Compression)
    install(CallLogging)
    install(ContentNegotiation) {
        json(
            contentType = ContentType.Application.Json,
            json = Json(
                DefaultJsonConfiguration.copy(
                    prettyPrint = true
                )
            )
        )
    }
    install(StatusPages) {
        exception<TodoNotFoundException> {
            val (code, response) = mapExceptionToResponse(NotFound, it)
            call.respond(code, response)
        }

        exception<InvalidTodoStatusException> {
            val (code, response) = mapExceptionToResponse(BadRequest, it)
            call.respond(code, response)
        }

        exception<SerializationException> {
            val (code, response) = mapExceptionToResponse(BadRequest, it)
            call.respond(code, response)
        }

        exception<Exception> {
            val (code, response) = mapExceptionToResponse(InternalServerError, it)
            call.respond(code, response)
        }
    }
    install(Routing) {
        TodoRouter(this)
    }
}

private fun mapExceptionToResponse(code: HttpStatusCode, exception: Throwable) = run {
    logger.error(exception)
    code to  ExceptionResponse(
        code = code.value,
        message = exception.localizedMessage ?: code.description,
        timestamp = System.currentTimeMillis()
    )
}
