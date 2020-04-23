package io.github.wellingtoncosta.todoapp.data.db

import com.squareup.sqldelight.db.SqlDriver
import io.github.wellingtoncosta.todoapp.TodoAppDatabase

expect fun setupDatabase(body: () -> SqlDriver): TodoAppDatabase
