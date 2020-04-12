package io.github.wellingtoncosta.todoapp.data.db

import android.content.ContentValues
import io.github.wellingtoncosta.todoapp.data.db.TodoContract.TodoEntry
import io.github.wellingtoncosta.todoapp.entity.Todo

fun Todo.asContentValues() = ContentValues().apply {
    put(TodoEntry.COLUMN_NAME_TITLE, title)
    put(TodoEntry.COLUMN_NAME_DETAILS, details)
    put(TodoEntry.COLUMN_NAME_STATUS, status.value)
}
