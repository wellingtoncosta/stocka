package io.github.wellingtoncosta.todoapp

import android.app.Application
import io.github.wellingtoncosta.todoapp.data.TodoRepositoryImpl
import io.github.wellingtoncosta.todoapp.data.db.createDatabase
import io.github.wellingtoncosta.todoapp.presentation.TodoViewModel
import io.github.wellingtoncosta.todoapp.presentation.TodosViewModel
import io.github.wellingtoncosta.todoapp.domain.TodoRepository

class App : Application() {

    private lateinit var todoAppDatabase: TodoAppDatabase

    private lateinit var todoRepository: TodoRepository

    lateinit var todosViewModelFactory: TodosViewModel.Factory private set

    lateinit var todoViewModeFactory: TodoViewModel.Factory private set

    override fun onCreate() {
        super.onCreate()
        todoAppDatabase = createDatabase()
        todoRepository = TodoRepositoryImpl(todoAppDatabase)
        todosViewModelFactory = TodosViewModel.Factory(todoRepository)
        todoViewModeFactory = TodoViewModel.Factory(todoRepository)
    }

}
