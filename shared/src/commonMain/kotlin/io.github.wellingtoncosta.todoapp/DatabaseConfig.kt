package io.github.wellingtoncosta.todoapp

import com.squareup.sqldelight.db.SqlDriver

expect fun setupDatabase(body: () -> SqlDriver): TodoAppDatabase
