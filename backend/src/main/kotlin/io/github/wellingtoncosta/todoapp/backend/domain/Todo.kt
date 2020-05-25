package io.github.wellingtoncosta.todoapp.backend.domain

import kotlinx.serialization.Serializable

@Serializable data class Todo(
    val id: String? = null,
    val title: String,
    val description: String?,
    val status: TodoStatus = TodoStatus.PLANNED
)
