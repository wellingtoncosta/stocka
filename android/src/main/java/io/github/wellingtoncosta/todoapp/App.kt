package io.github.wellingtoncosta.todoapp

import android.app.Application
import io.github.wellingtoncosta.todoapp.data.db.TodoDbHelper
import io.github.wellingtoncosta.todoapp.data.repository.TodoSqliteRepository
import io.github.wellingtoncosta.todoapp.presentation.TodosViewModel
import io.github.wellingtoncosta.todoapp.shared.domain.repository.TodoRepository

class App : Application() {

    private lateinit var todoHelper: TodoDbHelper

    lateinit var todoRepository: TodoRepository private set

    lateinit var todosViewModelFactory: TodosViewModel.Factory

    override fun onCreate() {
        super.onCreate()
        todoHelper = TodoDbHelper(applicationContext)
        todoRepository = TodoSqliteRepository(todoHelper)
        todosViewModelFactory = TodosViewModel.Factory(todoRepository)
    }

}
