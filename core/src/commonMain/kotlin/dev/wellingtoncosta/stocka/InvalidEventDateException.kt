package dev.wellingtoncosta.stocka

import kotlinx.datetime.LocalDate

class InvalidEventDateException(date: LocalDate) : RuntimeException(
    "There are events registered before $date"
)