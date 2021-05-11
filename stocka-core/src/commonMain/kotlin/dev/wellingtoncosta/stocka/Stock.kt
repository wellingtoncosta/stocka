package dev.wellingtoncosta.stocka

import kotlinx.datetime.LocalDate

class Stock<out T : Stock.Item> {

    sealed interface Item

    sealed class Event(
        val type: Type,
        open val amount: Int,
        open val date: LocalDate
    ) {
        enum class Type { INPUT, OUTPUT }

        data class Input(
            override val amount: Int,
            override val date: LocalDate,
        ) : Event(type = Type.INPUT, amount, date)

        data class Output(
            override val amount: Int,
            override val date: LocalDate,
        ) : Event(type = Type.OUTPUT, amount, date)
    }

    constructor(item: T) {
        this.id = null
        this.item = item
        this._events = mutableListOf<Event>()
    }

    constructor(id: String, item: T) {
        this.id = id
        this.item = item
        this._events = mutableListOf<Event>()
    }

    constructor(id: String, item: T, events: List<Event>) {
        this.id = id
        this.item = item
        this._events = events.toMutableList()
    }

    val id: String?

    val item: T

    private val _events: MutableList<Event>

    val events: List<Event>
        get() = _events

    val available: Int
        get() {
            var accumulator = 0
            events
                .sortedByDescending { it.date }
                .forEach {
                    when (it.type) {
                        Event.Type.INPUT -> accumulator += it.amount
                        Event.Type.OUTPUT -> accumulator -= it.amount
                    }
                }

            return accumulator
        }

    fun register(event: Event) {
        checkEventDate(event)

        checkAmountIntegrity(event)

        _events.add(event)
    }

    private fun checkEventDate(event: Event) {
        val hasPreviousEvents = events.any { it.date > event.date }
        if (hasPreviousEvents) {
            throw InvalidEventDateException(event.date)
        }
    }

    private fun checkAmountIntegrity(event: Event) {
        if (event.type == Event.Type.OUTPUT && event.amount > available) {
            throw InsufficientStockException(
                availableAmount = available,
                requestedAmount = event.amount
            )
        }
    }
}