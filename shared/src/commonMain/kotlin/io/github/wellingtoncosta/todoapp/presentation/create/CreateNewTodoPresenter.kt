package io.github.wellingtoncosta.todoapp.presentation.create

import io.github.wellingtoncosta.todoapp.Dispatchers
import io.github.wellingtoncosta.todoapp.domain.Todo
import io.github.wellingtoncosta.todoapp.domain.TodoRepository
import io.github.wellingtoncosta.todoapp.presentation.LifecyclePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class CreateNewTodoPresenter(
    private val repository: TodoRepository
) : LifecyclePresenter {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.ui)

    fun execute(todo: Todo, callback: (CreateNewTodoState) -> Unit) {
        scope.launch {
            callback(Loading)
            try {
                repository.save(todo)
                callback(Success)
            } catch (exception: Throwable) {
                callback(Error(cause = exception))
            }
        }
    }

    override fun destroy() {
        scope.cancel()
    }

}
