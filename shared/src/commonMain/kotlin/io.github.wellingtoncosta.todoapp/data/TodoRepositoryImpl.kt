package io.github.wellingtoncosta.todoapp.data

import io.github.wellingtoncosta.todoapp.TodoAppDatabase
import io.github.wellingtoncosta.todoapp.entity.Todo
import io.github.wellingtoncosta.todoapp.entity.TodoStatus
import io.github.wellingtoncosta.todoapp.repository.TodoRepository

class TodoRepositoryImpl(private val todoAppDatabase: TodoAppDatabase) : TodoRepository {

    override fun fetchAll(): List<Todo> {
        return todoAppDatabase.todoQueries
            .selectAll()
            .executeAsList()
            .map {
                Todo(
                    id = it.id.toInt(),
                    title = it.title,
                    description = it.description,
                    status = it.status
                )
            }
    }

    override fun save(todo: Todo) {
        todoAppDatabase.todoQueries.insert(
            title = todo.title,
            description = todo.description,
            status = todo.status
        )
    }

    override fun updateStatus(todoId: Long, staatus: TodoStatus) {
        todoAppDatabase.todoQueries.updateStatus(
            status = staatus,
            id = todoId
        )
    }

}