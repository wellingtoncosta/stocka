package io.github.wellingtoncosta.todoapp.data.db

import com.squareup.sqldelight.db.SqlDriver
import io.github.wellingtoncosta.todoapp.TodoAppDatabase

actual fun setupDatabase(body: () -> SqlDriver): TodoAppDatabase {
    val driver = body()
    return createQueryWrapper(driver).also {
        it.todoQueries.selectAll().executeAsList()
    }
}
