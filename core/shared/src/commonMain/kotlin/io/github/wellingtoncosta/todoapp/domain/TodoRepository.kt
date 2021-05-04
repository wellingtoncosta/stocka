package io.github.wellingtoncosta.todoapp.domain

interface TodoRepository {

    suspend fun fetchAll(): List<Todo>

    suspend fun save(todo: Todo)

    suspend fun updateStatus(todoId: Long, staatus: TodoStatus)

}
