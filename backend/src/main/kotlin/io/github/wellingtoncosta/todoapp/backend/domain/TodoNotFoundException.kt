package io.github.wellingtoncosta.todoapp.backend.domain

class TodoNotFoundException(id: String) : RuntimeException("Todo for ID $id does not exist.")
