package io.github.wellingtoncosta.todoapp.data

import com.squareup.sqldelight.db.SqlDriver
import io.github.wellingtoncosta.todoapp.TodoAppDatabase

fun createQueryWrapper(driver: SqlDriver) = TodoAppDatabase(driver = driver)

object Schema : SqlDriver.Schema by TodoAppDatabase.Schema {
    override fun create(driver: SqlDriver) {
        TodoAppDatabase.Schema.create(driver)
    }
}
