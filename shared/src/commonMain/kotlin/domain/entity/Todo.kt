package io.github.wellingtoncosta.todoapp.shared.domain.entity

data class Todo(
    val id: Long,
    val title: String,
    val details: String?,
    val status: TodoStatus = TodoStatus.PLANNED
)
