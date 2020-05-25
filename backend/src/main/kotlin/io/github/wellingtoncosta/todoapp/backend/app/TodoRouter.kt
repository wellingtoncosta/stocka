package io.github.wellingtoncosta.todoapp.backend.app

import io.github.wellingtoncosta.todoapp.backend.domain.TodoStatus
import io.github.wellingtoncosta.todoapp.backend.infrastructure.TodoRepository
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.patch
import io.ktor.routing.post
import io.ktor.routing.route

object TodoRouter  {

    operator fun invoke(routing: Routing) {
        routing.apply {
            route("/todos") {
                get { call.respond(TodoRepository.getAll()) }
                get("/{id}") { call.respond(TodoRepository.getById(call.getIdFromPath())) }
                post { call.respond(TodoRepository.save(call.receive())) }
                patch("/{id}/status/{status}") {
                    call.respond(
                        TodoRepository.updateStatus(
                            call.getIdFromPath(),
                            TodoStatus.of(call.getStatusFromPath())
                        )
                    )
                }
            }
        }
    }

}

private fun ApplicationCall.getIdFromPath() = parameters["id"] ?: ""
private fun ApplicationCall.getStatusFromPath() = parameters["status"] ?: ""
