package dev.wellingtoncosta.stocka.data.db

import com.squareup.sqldelight.EnumColumnAdapter

@Suppress("FunctionName") // Emulating a constructor.
fun SeedAdapter() = Seed.Adapter(
    measuringUnitAdapter = EnumColumnAdapter(),
    expirationDateAdapter = LocalDateColumnAdapter()
)