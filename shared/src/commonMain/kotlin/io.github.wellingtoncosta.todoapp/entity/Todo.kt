package io.github.wellingtoncosta.todoapp.entity

data class Todo(
    val id: Int? = null,
    val title: String,
    val details: String?,
    val status: TodoStatus = TodoStatus.PLANNED
)
