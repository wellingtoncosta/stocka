package io.github.wellingtoncosta.todoapp.backend.domain

class InvalidTodoStatusException(
    status: String
) : RuntimeException("Todo status '$status' is not a valid.")
