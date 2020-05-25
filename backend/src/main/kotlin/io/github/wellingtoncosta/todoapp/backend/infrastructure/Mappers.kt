package io.github.wellingtoncosta.todoapp.backend.infrastructure

import io.github.wellingtoncosta.todoapp.backend.domain.Todo
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toTodo() = Todo(
    id = this[TodoTable.id],
    title = this[TodoTable.title],
    description = this[TodoTable.description],
    status = this[TodoTable.status]
)
