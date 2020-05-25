package io.github.wellingtoncosta.todoapp.domain

import io.github.wellingtoncosta.todoapp.domain.Todo
import io.github.wellingtoncosta.todoapp.domain.TodoStatus

interface TodoRepository {

    fun fetchAll(): List<Todo>

    fun save(todo: Todo)

    fun updateStatus(todoId: Long, staatus: TodoStatus)

}
