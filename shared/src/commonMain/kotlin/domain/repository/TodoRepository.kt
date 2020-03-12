package io.github.wellingtoncosta.todoapp.shared.domain.repository

import io.github.wellingtoncosta.todoapp.shared.domain.entity.Todo

interface TodoRepository {

    fun fetchAll(): List<Todo>

    fun save(todo: Todo)

}
