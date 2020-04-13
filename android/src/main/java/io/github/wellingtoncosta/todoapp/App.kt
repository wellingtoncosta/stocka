package io.github.wellingtoncosta.todoapp

import android.app.Application
import com.squareup.sqldelight.android.AndroidSqliteDriver
import io.github.wellingtoncosta.todoapp.data.Schema
import io.github.wellingtoncosta.todoapp.data.TodoSqlDelightRepository
import io.github.wellingtoncosta.todoapp.presentation.TodoViewModel
import io.github.wellingtoncosta.todoapp.presentation.TodosViewModel
import io.github.wellingtoncosta.todoapp.repository.TodoRepository

class App : Application() {

    private lateinit var todoAppDatabase: TodoAppDatabase

    private lateinit var todoRepository: TodoRepository

    lateinit var todosViewModelFactory: TodosViewModel.Factory private set

    lateinit var todoViewModeFactory: TodoViewModel.Factory private set

    override fun onCreate() {
        super.onCreate()

        todoAppDatabase = setupDatabase {
            AndroidSqliteDriver(Schema, applicationContext, "todoapp.db")
        }

        todoRepository =
            TodoSqlDelightRepository(
                todoAppDatabase
            )
        todosViewModelFactory = TodosViewModel.Factory(todoRepository)
        todoViewModeFactory = TodoViewModel.Factory(todoRepository)
    }

}
