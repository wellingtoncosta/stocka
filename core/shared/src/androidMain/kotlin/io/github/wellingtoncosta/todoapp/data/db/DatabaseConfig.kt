package io.github.wellingtoncosta.todoapp.data.db

import android.app.Application
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import io.github.wellingtoncosta.todoapp.TodoAppDatabase

actual fun setupDatabase(body: () -> SqlDriver): TodoAppDatabase {
    val driver = body()
    return createQueryWrapper(driver)
}

fun Application.createDatabase(): TodoAppDatabase {
    return setupDatabase {
        AndroidSqliteDriver(Schema, applicationContext, "todoapp.db")
    }
}
