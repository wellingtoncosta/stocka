package io.github.wellingtoncosta.todoapp

import android.app.Application
import io.github.wellingtoncosta.todoapp.data.db.TodoDbHelper
import io.github.wellingtoncosta.todoapp.data.repository.TodoSqliteRepository
import io.github.wellingtoncosta.todoapp.presentation.TodoViewModel
import io.github.wellingtoncosta.todoapp.presentation.TodosViewModel
import io.github.wellingtoncosta.todoapp.shared.domain.repository.TodoRepository

class App : Application() {

    private lateinit var todoHelper: TodoDbHelper

    private lateinit var todoRepository: TodoRepository

    lateinit var todosViewModelFactory: TodosViewModel.Factory private set

    lateinit var todoViewModeFactory: TodoViewModel.Factory private set

    override fun onCreate() {
        super.onCreate()
        todoHelper = TodoDbHelper(applicationContext)
        todoRepository = TodoSqliteRepository(todoHelper)
        todosViewModelFactory = TodosViewModel.Factory(todoRepository)
        todoViewModeFactory = TodoViewModel.Factory(todoRepository)
    }

}
