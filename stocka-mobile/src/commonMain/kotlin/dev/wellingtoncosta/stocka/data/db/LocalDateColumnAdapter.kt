package dev.wellingtoncosta.stocka.data.db

import com.squareup.sqldelight.ColumnAdapter
import kotlinx.datetime.LocalDate

class LocalDateColumnAdapter : ColumnAdapter<LocalDate, String> {
    override fun decode(databaseValue: String): LocalDate {
        return LocalDate.parse(databaseValue)
    }

    override fun encode(value: LocalDate): String {
        return value.toString()
    }
}