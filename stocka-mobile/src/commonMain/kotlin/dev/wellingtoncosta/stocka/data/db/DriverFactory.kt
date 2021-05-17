package dev.wellingtoncosta.stocka.data.db

import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun create(): SqlDriver
}