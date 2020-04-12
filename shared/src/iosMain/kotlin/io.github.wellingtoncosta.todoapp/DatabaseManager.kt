package io.github.wellingtoncosta.todoapp

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import io.github.wellingtoncosta.todoapp.data.Schema
import io.github.wellingtoncosta.todoapp.data.createQueryWrapper
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze

object DatabaseManager {

    private val driver = AtomicReference<SqlDriver?>(null)
    private val database = AtomicReference<TodoAppDatabase?>(null)

    private fun setup(driver: SqlDriver) {
        val db = createQueryWrapper(driver)
        this.driver.value = driver.freeze()
        this.database.value = db.freeze()
    }

    internal fun clear() {
        this.driver.value!!.close()
        this.database.value = null
        this.driver.value = null
    }

    //Called from Swift
    @Suppress("unused")
    fun defaultDriver() {
        setup(NativeSqliteDriver(Schema, "todoappdb"))
    }

    val instance: TodoAppDatabase
        get() = this.database.value!!

}