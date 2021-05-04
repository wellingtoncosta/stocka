package io.github.wellingtoncosta.todoapp.presentation.list

import io.github.wellingtoncosta.todoapp.Dispatchers
import io.github.wellingtoncosta.todoapp.domain.TodoRepository
import io.github.wellingtoncosta.todoapp.presentation.LifecyclePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ListTodosPresenter(
    private val repository: TodoRepository
) : LifecyclePresenter {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.ui)

    fun execute(callback: (ListTodosState) -> Unit) {
        scope.launch {
            callback(Loading)
            try {
                callback(
                    Success(
                        results = repository.fetchAll()
                    )
                )
            } catch (exception: Throwable) {
                callback(
                    Error(
                        cause = exception
                    )
                )
            }
        }
    }

    override fun destroy() {
        scope.cancel()
    }

}
