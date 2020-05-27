package io.github.wellingtoncosta.todoapp.presentation.list

import io.github.wellingtoncosta.todoapp.domain.Todo

sealed class ListTodosState
    object Loading : ListTodosState()
    data class Success(val results: List<Todo>) : ListTodosState()
    data class Error(val cause: Throwable) : ListTodosState()
