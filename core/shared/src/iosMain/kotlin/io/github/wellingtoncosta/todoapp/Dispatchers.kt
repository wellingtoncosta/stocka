package io.github.wellingtoncosta.todoapp

import kotlinx.coroutines.*
import platform.darwin.*
import kotlin.coroutines.CoroutineContext

actual object Dispatchers {

    actual val ui: CoroutineDispatcher = NsQueueDispatcher

    actual val background: CoroutineDispatcher = NsQueueDispatcher

}

// Currently, Kotlin/Native only supports coroutines only on main thread
// For further details, please see https://github.com/Kotlin/kotlinx.coroutines/issues/462
private object NsQueueDispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        val queue = dispatch_get_main_queue()
        dispatch_async(queue) {
            block.run()
        }
    }
}
