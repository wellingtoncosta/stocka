package io.github.wellingtoncosta.todoapp.data.api

import io.github.wellingtoncosta.todoapp.domain.Todo
import io.github.wellingtoncosta.todoapp.domain.TodoStatus
import kotlinx.serialization.Serializable

@Serializable data class TodoResponse(
    val id: String,
    val title: String,
    val description: String? = null,
    val status: TodoStatus
) {

    fun toDomain() = Todo(
        externalId = id,
        title = title,
        description = description,
        status = status
    )

}
