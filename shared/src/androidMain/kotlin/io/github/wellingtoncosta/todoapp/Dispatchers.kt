package io.github.wellingtoncosta.todoapp

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual object Dispatchers {

    actual val ui: CoroutineDispatcher = Dispatchers.Main

    actual val background: CoroutineDispatcher = Dispatchers.Default

}
