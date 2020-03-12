package io.github.wellingtoncosta.todoapp.shared

expect fun platformName(): String

fun createGreeting(): String {
    return "Hi! I'm running on ${platformName()}. \\o/"
}
