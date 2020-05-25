package io.github.wellingtoncosta.todoapp.backend.infrastructure

import io.github.wellingtoncosta.todoapp.backend.domain.Todo
import io.github.wellingtoncosta.todoapp.backend.domain.TodoNotFoundException
import io.github.wellingtoncosta.todoapp.backend.domain.TodoRepository
import io.github.wellingtoncosta.todoapp.backend.domain.TodoStatus
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object TodoRepository : TodoRepository {

    override fun getAll() = transaction {
        TodoTable.selectAll().map { it.toTodo() }
    }

    override fun getById(id: String) = transaction {
        TodoTable.select { TodoTable.id eq(id) }
            .firstOrNull()
            ?.toTodo() ?: throw TodoNotFoundException(id)
    }

    override fun save(todo: Todo) = transaction {
        val todoId = TodoTable.insert {
            it[id] = UlidWrapper.getNext()
            it[title] = todo.title
            it[description] = todo.description
            it[status] = todo.status
        } get TodoTable.id

        todo.copy(id = todoId)
    }

    override fun updateStatus(id: String, status: TodoStatus) {
        transaction {
            TodoTable.update({ TodoTable.id eq id }) {
                it[TodoTable.status] = status
            }
        }
    }

}
