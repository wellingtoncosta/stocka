package io.github.wellingtoncosta.todoapp.domain

data class Todo(
    val id: Int? = null,
    val externalId: String? = null,
    val title: String,
    val description: String?,
    val status: TodoStatus = TodoStatus.PLANNED
)
