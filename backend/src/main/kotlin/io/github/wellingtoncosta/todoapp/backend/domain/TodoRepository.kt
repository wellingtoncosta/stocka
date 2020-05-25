package io.github.wellingtoncosta.todoapp.backend.domain

interface TodoRepository {

    fun getAll(): List<Todo>

    fun getById(id: String): Todo

    fun save(todo: Todo): Todo

    fun updateStatus(id: String, status: TodoStatus)

}
