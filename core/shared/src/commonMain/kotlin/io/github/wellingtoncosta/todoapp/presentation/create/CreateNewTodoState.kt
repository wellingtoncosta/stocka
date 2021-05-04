package io.github.wellingtoncosta.todoapp.presentation.create

sealed class CreateNewTodoState
    object Loading : CreateNewTodoState()
    object Success : CreateNewTodoState()
    data class Error(val cause: Throwable) : CreateNewTodoState()
