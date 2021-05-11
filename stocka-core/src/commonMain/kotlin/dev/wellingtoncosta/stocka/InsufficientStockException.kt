package dev.wellingtoncosta.stocka

class InsufficientStockException(availableAmount: Int, requestedAmount: Int) : RuntimeException(
    "The available amount is $availableAmount, but was requested $requestedAmount"
)