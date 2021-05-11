package dev.wellingtoncosta.stocka

import kotlinx.datetime.LocalDate

data class Fertilizer(
    val id: String? = null,
    val name: String,
    val manufacturer: String,
    val type: FertilizerType,
    val measuringUnit: MeasuringUnit,
    val expirationDate: LocalDate
) : Stock.Item