package io.github.wellingtoncosta.todoapp.data.api

import io.github.wellingtoncosta.todoapp.domain.TodoStatus
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.features.HttpResponseValidator
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

expect val engine: HttpClientEngineFactory<HttpClientEngineConfig>

interface TodoApi {

    suspend fun getAll(): List<TodoResponse>

    suspend fun save(request: SaveTodoRequest): TodoResponse

    suspend fun patchStatus(id: String, status: TodoStatus)

    class Impl : TodoApi {

        private val baseUrl: String = "https://todo-app-kmp-backend.herokuapp.com"

        private val json = Json(JsonConfiguration.Stable.copy(
            ignoreUnknownKeys = true,
            useArrayPolymorphism = true
        ))

        private val client = HttpClient(engine) {
            configureJson()
            configureLogging()
            configureDefaultRequest()
            configureExceptionMapping()
        }

        private fun HttpClientConfig<*>.configureJson() {
            install(JsonFeature) {
                serializer = KotlinxSerializer(json)
            }
        }

        private fun HttpClientConfig<*>.configureLogging() {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }

        private fun HttpClientConfig<*>.configureDefaultRequest() {
            defaultRequest {
                url.takeFrom(URLBuilder().takeFrom(baseUrl).apply {
                    encodedPath += url.encodedPath
                })
            }
        }

        private fun HttpClientConfig<*>.configureExceptionMapping() {
            HttpResponseValidator {
                validateResponse {
                    if (!it.status.isSuccess()) {
                        when (it.status) {
                            HttpStatusCode.BadRequest -> throw BadRequest()
                            HttpStatusCode.NotFound -> throw NotFound()
                            HttpStatusCode.InternalServerError -> throw InternalServerError()
                            else -> throw Unknown()
                        }
                    }
                }
            }
        }

        override suspend fun getAll(): List<TodoResponse> {
            return client.get {
                url { path("todos") }
            }
        }

        override suspend fun save(request: SaveTodoRequest): TodoResponse {
            return client.post {
                url { path("todos") }
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                body = request
            }
        }

        override suspend fun patchStatus(id: String, status: TodoStatus) {
            client.patch<Unit> {
                url { path(id, "status", status.toString()) }
            }
        }

    }

}
