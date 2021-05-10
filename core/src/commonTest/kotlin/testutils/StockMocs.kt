package testutils

import dev.wellingtoncosta.stocka.Seed
import kotlinx.datetime.LocalDate

fun oneSeed() = Seed(
    id = "asdf1234",
    name = "Fake Seed",
    manufacturer = "Fake Manufacturer, Inc",
    expirationDate = LocalDate.parse("2021-12-31")
)