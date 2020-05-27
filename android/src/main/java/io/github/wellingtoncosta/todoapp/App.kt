package io.github.wellingtoncosta.todoapp

import android.app.Application
import io.github.wellingtoncosta.todoapp.data.TodoRepositoryImpl
import io.github.wellingtoncosta.todoapp.data.db.createDatabase
import io.github.wellingtoncosta.todoapp.domain.TodoRepository
import io.github.wellingtoncosta.todoapp.presentation.create.CreateNewTodoPresenter
import io.github.wellingtoncosta.todoapp.presentation.list.ListTodosPresenter

class App : Application() {

    private lateinit var todoAppDatabase: TodoAppDatabase

    private lateinit var todoRepository: TodoRepository

    val listTodosPresenter: ListTodosPresenter
        get() = ListTodosPresenter(todoRepository)

    val createNewTodoPresenter: CreateNewTodoPresenter
        get() = CreateNewTodoPresenter(todoRepository)

    override fun onCreate() {
        super.onCreate()

        todoAppDatabase = createDatabase()

        todoRepository = TodoRepositoryImpl(todoAppDatabase)
    }

}
