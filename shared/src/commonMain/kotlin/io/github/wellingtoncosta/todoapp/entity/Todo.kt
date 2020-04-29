package io.github.wellingtoncosta.todoapp

import io.github.wellingtoncosta.todoapp.entity.TodoStatus

data class Todo(
    val id: Int? = null,
    val title: String,
    val description: String?,
    val status: TodoStatus = TodoStatus.PLANNED
)
