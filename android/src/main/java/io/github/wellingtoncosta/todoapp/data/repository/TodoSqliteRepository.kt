package io.github.wellingtoncosta.todoapp.data.repository

import android.provider.BaseColumns._ID
import io.github.wellingtoncosta.todoapp.data.db.TodoContract.TodoEntry.COLUMN_NAME_DETAILS
import io.github.wellingtoncosta.todoapp.data.db.TodoContract.TodoEntry.COLUMN_NAME_STATUS
import io.github.wellingtoncosta.todoapp.data.db.TodoContract.TodoEntry.COLUMN_NAME_TITLE
import io.github.wellingtoncosta.todoapp.data.db.TodoContract.TodoEntry.TABLE_NAME
import io.github.wellingtoncosta.todoapp.data.db.TodoDbHelper
import io.github.wellingtoncosta.todoapp.data.db.asContentValues
import io.github.wellingtoncosta.todoapp.entity.Todo
import io.github.wellingtoncosta.todoapp.entity.TodoStatus
import io.github.wellingtoncosta.todoapp.entity.TodoStatus.Companion.asTodoStatus
import io.github.wellingtoncosta.todoapp.repository.TodoRepository

class TodoSqliteRepository(private val helper: TodoDbHelper) : TodoRepository {

    private val readableDb by lazy {
        helper.readableDatabase
            ?: throw IllegalStateException("Database isn't available.")
    }

    private val writableDb by lazy {
        helper.writableDatabase
            ?: throw IllegalStateException("Database isn't available.")
    }

    override fun fetchAll(): List<Todo> {
        val cursor = readableDb.query(TABLE_NAME, null, null, null, null, null, null)
        val results = mutableListOf<Todo>()

        with(cursor) {
            use {
                while (moveToNext()) {
                    results.add(
                        Todo(
                            id = getInt(getColumnIndexOrThrow(_ID)),
                            title = getString(getColumnIndexOrThrow(COLUMN_NAME_TITLE)),
                            details = getString(getColumnIndexOrThrow(COLUMN_NAME_DETAILS)),
                            status = getInt(getColumnIndexOrThrow(COLUMN_NAME_STATUS)).asTodoStatus()
                        )
                    )
                }
            }
        }

        return results
    }

    override fun save(todo: Todo) {
        writableDb.insert(TABLE_NAME, null, todo.asContentValues())
    }

    override fun updateStatus(todoId: Long, staatus: TodoStatus) {

    }

}
