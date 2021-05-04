package dev.wellingtoncosta.catalog

import kotlinx.datetime.LocalDate

data class Seed(
    val id: String? = null,
    val name: String,
    val manufacturer: String,
    val measuringUnit: MeasuringUnit = MeasuringUnit.KILOGRAM,
    val expirationDate: LocalDate
)