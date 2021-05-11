package dev.wellingtoncosta.stocka

import kotlinx.datetime.LocalDate
import testutils.oneSeed
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class StockTest {

    @Test
    fun `should create a new one successfully`() {
        val seed = oneSeed()

        val stock = Stock(item = seed)

        assertTrue { stock.available == 0 }
    }

    @Test
    fun `should supply successfully`() {
        val seed = oneSeed()

        val stock = Stock(item = seed)

        assertTrue { stock.available == 0 }

        stock.register(Stock.Event.Input(amount = 100, date = LocalDate.parse("2021-05-10")))

        assertTrue { stock.available == 100 }
    }

    @Test
    fun `should try to register a new event with a past date`() {
        val seed = oneSeed()

        val stock = Stock(item = seed)

        stock.register(Stock.Event.Input(amount = 100, date = LocalDate.parse("2021-05-10")))

        assertTrue { stock.available == 100 }

        assertFailsWith(InvalidEventDateException::class) {
            stock.register(Stock.Event.Input(amount = 100, date = LocalDate.parse("2021-01-01")))
        }
    }

    @Test
    fun `should try to use a certain amount greater than the available amount`() {
        val seed = oneSeed()

        val stock = Stock(item = seed)

        stock.register(Stock.Event.Input(amount = 100, date = LocalDate.parse("2021-05-10")))

        assertTrue { stock.available == 100 }

        assertFailsWith(InsufficientStockException::class) {
            stock.register(Stock.Event.Output(amount = 200, date = LocalDate.parse("2021-05-10")))
        }
    }

    @Test
    fun `should calculate the available amount successfully`() {
        val seed = oneSeed()

        val stock = Stock(item = seed)

        stock.register(Stock.Event.Input(amount = 100, date = LocalDate.parse("2021-05-10")))

        assertTrue { stock.available == 100 }

        stock.register(Stock.Event.Output(amount = 10, date = LocalDate.parse("2021-05-10")))

        assertTrue { stock.available == 90 }

        stock.register(Stock.Event.Output(amount = 40, date = LocalDate.parse("2021-05-10")))

        assertTrue { stock.available == 50 }
    }
}