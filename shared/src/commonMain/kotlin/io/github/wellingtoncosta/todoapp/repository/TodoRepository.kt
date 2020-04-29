package io.github.wellingtoncosta.todoapp.repository

import io.github.wellingtoncosta.todoapp.Todo
import io.github.wellingtoncosta.todoapp.entity.TodoStatus

interface TodoRepository {

    fun fetchAll(): List<Todo>

    fun save(todo: Todo)

    fun updateStatus(todoId: Long, staatus: TodoStatus)

}
