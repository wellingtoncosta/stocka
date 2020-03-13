package io.github.wellingtoncosta.todoapp.presentation

import androidx.lifecycle.*
import io.github.wellingtoncosta.todoapp.shared.domain.entity.Todo
import io.github.wellingtoncosta.todoapp.shared.domain.repository.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) :  ViewModel() {

    private val _saving = MutableLiveData<Boolean>()
    private val _saved = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<Throwable>()

    val saving: LiveData<Boolean>
        get() = _saving

    val saved: LiveData<Boolean>
        get() = _saved

    val error: LiveData<Throwable>
        get() = _error

    fun save(todo: Todo) {
        viewModelScope.launch {
            try {
                _saving.value = true
                repository.save(todo)
                _saved.value = true
            } catch (error: Throwable) {
                _saved.value = false
                _error.value = error
            } finally {
                _saving.value = false
            }
        }
    }

    class Factory(private val repository: TodoRepository) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TodoViewModel(repository) as T
        }

        fun create() = this.create(TodoViewModel::class.java)
    }

}
