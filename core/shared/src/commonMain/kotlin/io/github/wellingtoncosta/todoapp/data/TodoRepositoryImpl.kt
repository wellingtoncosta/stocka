package io.github.wellingtoncosta.todoapp.data

import io.github.wellingtoncosta.todoapp.TodoAppDatabase
import io.github.wellingtoncosta.todoapp.data.api.SaveTodoRequest
import io.github.wellingtoncosta.todoapp.data.api.TodoApi
import io.github.wellingtoncosta.todoapp.domain.Todo
import io.github.wellingtoncosta.todoapp.domain.TodoRepository
import io.github.wellingtoncosta.todoapp.domain.TodoStatus

class TodoRepositoryImpl(
    private val todoAppDatabase: TodoAppDatabase,
    private val todoApi: TodoApi
) : TodoRepository {

    override suspend fun fetchAll(): List<Todo> {
        return try {
            todoApi.getAll()
                .map { it.toDomain() }
                .filterNot { exists(it.externalId!!) }
                .forEach { insert(it) }
                .let { selectAllFromDatabase() }
        } catch (exception: Throwable) {
            selectAllFromDatabase()
        }
    }

    private fun exists(externalId: String): Boolean {
        return todoAppDatabase.todoQueries
            .selectByExternalId(externalId)
            .executeAsOneOrNull() != null
    }

    private fun selectAllFromDatabase(): List<Todo> {
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

    private fun insert(todo: Todo) {
        todoAppDatabase.todoQueries.insert(
            external_id = todo.externalId,
            title = todo.title,
            description = todo.description,
            status = todo.status
        )
    }

    override suspend fun save(todo: Todo) {
        todoApi.save(SaveTodoRequest.from(todo))
            .toDomain()
            .let { insert(it) }
    }

    override suspend fun updateStatus(todoId: Long, staatus: TodoStatus) {
        TODO("Not yet implemented")
    }

}