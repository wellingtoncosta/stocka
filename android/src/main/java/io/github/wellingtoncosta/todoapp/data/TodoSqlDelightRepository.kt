package io.github.wellingtoncosta.todoapp.data

import io.github.wellingtoncosta.todoapp.TodoAppDatabase
import io.github.wellingtoncosta.todoapp.entity.Todo
import io.github.wellingtoncosta.todoapp.entity.TodoStatus
import io.github.wellingtoncosta.todoapp.entity.TodoStatus.Companion.asTodoStatus
import io.github.wellingtoncosta.todoapp.repository.TodoRepository

class TodoSqlDelightRepository(private val todoAppDatabase: TodoAppDatabase) : TodoRepository {

    override fun fetchAll(): List<Todo> {
        return todoAppDatabase.todoQueries
            .selectAll()
            .executeAsList()
            .map { record ->
                Todo(
                    id = record.id.toInt(),
                    title = record.title,
                    description = record.description,
                    status = record.status.toInt().asTodoStatus()
                )
            }
    }

    override fun save(todo: Todo) {
        todoAppDatabase.todoQueries.insert(
            title = todo.title,
            description = todo.description,
            status = todo.status.value.toLong()
        )
    }

    override fun updateStatus(todoId: Long, staatus: TodoStatus) {
        todoAppDatabase.todoQueries.updateStatus(
            status = staatus.value.toLong(),
            id = todoId
        )
    }

}