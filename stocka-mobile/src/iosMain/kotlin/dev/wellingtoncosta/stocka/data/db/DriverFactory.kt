package dev.wellingtoncosta.stocka.data.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun create(): SqlDriver {
         return NativeSqliteDriver(StockaDatabase.Schema, "stocka.db")
    }
}