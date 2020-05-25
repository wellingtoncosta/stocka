package io.github.wellingtoncosta.todoapp.domain

enum class TodoStatus {
    PLANNED,
    IN_PROGRESS,
    DONE;

    fun capitalized(): String {
        val status = toString().toLowerCase()
        val firstLetter = status.substring(0, 1).toUpperCase()
        return "$firstLetter${status.substring(1)}"
    }

}
