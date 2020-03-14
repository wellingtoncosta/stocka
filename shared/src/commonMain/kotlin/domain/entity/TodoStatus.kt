package io.github.wellingtoncosta.todoapp.shared.domain.entity

enum class TodoStatus(val value: Int) {
    PLANNED(0),
    IN_PROGRESS(1),
    DONE(2);

    fun capitalized(): String {
        val status = toString().toLowerCase()
        val firstLetter = status.substring(0, 1).toUpperCase()
        return "$firstLetter${status.substring(1)}"
    }

    companion object {
        fun Int.asTodoStatus() = values().first { it.value == this }
    }

}
