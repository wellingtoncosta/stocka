package dev.wellingtoncosta.stocka.data.db

import com.squareup.sqldelight.EnumColumnAdapter

@Suppress("FunctionName") // Emulating a constructor.
fun DefensiveAdapter() = Defensive.Adapter(
    typeAdapter = EnumColumnAdapter(),
    measuringUnitAdapter = EnumColumnAdapter(),
    expirationDateAdapter = LocalDateColumnAdapter()
)