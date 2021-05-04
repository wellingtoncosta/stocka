package io.github.wellingtoncosta.todoapp

import kotlinx.coroutines.CoroutineDispatcher

expect object Dispatchers {

    val ui: CoroutineDispatcher

    val background: CoroutineDispatcher

}
