package io.github.wellingtoncosta.todoapp.shared.domain.entity

data class Todo(
    val id: Int? = null,
    val title: String,
    val details: String?,
    val status: TodoStatus = TodoStatus.PLANNED
)
