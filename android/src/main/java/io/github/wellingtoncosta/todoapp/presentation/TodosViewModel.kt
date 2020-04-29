package io.github.wellingtoncosta.todoapp.presentation

import androidx.lifecycle.*
import io.github.wellingtoncosta.todoapp.Todo
import io.github.wellingtoncosta.todoapp.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodosViewModel(private val repository: TodoRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    private val _todos = MutableLiveData<List<Todo>>()
    private val _error = MutableLiveData<Throwable>()

    val loading: LiveData<Boolean>
        get() = _loading

    val todos: LiveData<List<Todo>>
        get() = _todos

    val error: LiveData<Throwable>
        get() = _error

    fun load() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _todos.value = withContext(Dispatchers.IO) {
                    repository.fetchAll()
                }
            } catch (error: Throwable) {
                _error.value = error
            } finally {
                _loading.value = false
            }
        }
    }

    class Factory(private val repository: TodoRepository) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TodosViewModel(repository) as T
        }

        fun create() = this.create(TodosViewModel::class.java)
    }

}