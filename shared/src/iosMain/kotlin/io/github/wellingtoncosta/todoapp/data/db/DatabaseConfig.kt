package io.github.wellingtoncosta.todoapp.data.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import io.github.wellingtoncosta.todoapp.TodoAppDatabase

actual fun setupDatabase(body: () -> SqlDriver): TodoAppDatabase {
    val driver = body()
    return createQueryWrapper(driver)
}

@Suppress("unused") // called from Swift code
fun createDatabase(): TodoAppDatabase {
    return setupDatabase {
        NativeSqliteDriver(Schema, "todoapp.db")
    }
}
