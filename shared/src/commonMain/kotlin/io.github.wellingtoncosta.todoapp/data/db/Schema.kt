package io.github.wellingtoncosta.todoapp.data.db

import com.squareup.sqldelight.EnumColumnAdapter
import com.squareup.sqldelight.db.SqlDriver
import io.github.wellingtoncosta.todoapp.Todos
import io.github.wellingtoncosta.todoapp.TodoAppDatabase

fun createQueryWrapper(driver: SqlDriver): TodoAppDatabase {
    return TodoAppDatabase(
        driver = driver,
        todosAdapter = Todos.Adapter(
            statusAdapter = EnumColumnAdapter()
        )
    )
}

object Schema : SqlDriver.Schema by TodoAppDatabase.Schema {
    override fun create(driver: SqlDriver) {
        TodoAppDatabase.Schema.create(driver)
    }
}
