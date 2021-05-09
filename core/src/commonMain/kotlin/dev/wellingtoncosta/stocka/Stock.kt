package dev.wellingtoncosta.stocka

data class Stock<out T : Stock.Item>(
    val id: String? = null,
    val item: T,
    val amount: Int
) {
    sealed interface Item
}