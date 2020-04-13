package io.github.wellingtoncosta.todoapp

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import io.github.wellingtoncosta.todoapp.data.Schema
import io.github.wellingtoncosta.todoapp.data.createQueryWrapper

actual fun setupDatabase(body: () -> SqlDriver): TodoAppDatabase {
    val driver = body()
    return createQueryWrapper(driver)
}

fun createDatabase(): TodoAppDatabase {
    return setupDatabase {
        NativeSqliteDriver(Schema, "todoapp.db")
    }
}
