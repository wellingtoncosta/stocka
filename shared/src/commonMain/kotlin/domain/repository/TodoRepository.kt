package io.github.wellingtoncosta.todoapp.shared.domain.repository

import io.github.wellingtoncosta.todoapp.shared.domain.entity.Todo
import io.github.wellingtoncosta.todoapp.shared.domain.entity.TodoStatus

interface TodoRepository {

    fun fetchAll(): List<Todo>

    fun save(todo: Todo)

    fun updateStatus(todoId: Long, staatus: TodoStatus)

}
