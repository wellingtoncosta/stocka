package io.github.wellingtoncosta.todoapp.data.api

import io.github.wellingtoncosta.todoapp.domain.Todo
import io.github.wellingtoncosta.todoapp.domain.TodoStatus
import kotlinx.serialization.Serializable

@Serializable data class SaveTodoRequest(
    val title: String,
    val description: String?,
    val status: TodoStatus
) {
    companion object {
        fun from(todo: Todo) = SaveTodoRequest(
            title = todo.title,
            description = todo.description,
            status = todo.status
        )
    }
}