package dev.wellingtoncosta.stocka.data.db

import com.squareup.sqldelight.EnumColumnAdapter

@Suppress("FunctionName") // Emulating a constructor.
fun FertilizerAdapter() = Fertilizer.Adapter(
    typeAdapter = EnumColumnAdapter(),
    measuringUnitAdapter = EnumColumnAdapter(),
    expirationDateAdapter = LocalDateColumnAdapter()
)