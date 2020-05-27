package io.github.wellingtoncosta.todoapp.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.github.wellingtoncosta.todoapp.App
import io.github.wellingtoncosta.todoapp.R
import io.github.wellingtoncosta.todoapp.databinding.ActivityListTodosBinding
import io.github.wellingtoncosta.todoapp.presentation.list.Loading
import io.github.wellingtoncosta.todoapp.presentation.list.Error
import io.github.wellingtoncosta.todoapp.presentation.list.ListTodosPresenter
import io.github.wellingtoncosta.todoapp.presentation.list.ListTodosState
import io.github.wellingtoncosta.todoapp.presentation.list.Success

class ListTodosActivity : AppCompatActivity() {

    private var _binding: ActivityListTodosBinding? = null
    private val binding get() = _binding!!

    private val todosAdapter = ListTodosAdapter()

    private lateinit var presenter: ListTodosPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = (applicationContext as App).listTodosPresenter

        setupBinding()

        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        presenter.execute(this::handleState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        presenter.destroy()
    }

    private fun handleState(state: ListTodosState) {
        when (state) {
            is Loading -> refreshing(true)
            is Success -> handleSuccess(state)
            is Error -> handleError(state)
        }
    }

    private fun refreshing(value: Boolean) {
        binding.swipeLayout.isRefreshing = value
    }

    private fun handleSuccess(success: Success) {
        refreshing(false).also {
            todosAdapter.dataSource = success.results
        }
    }

    private fun handleError(error: Error) {
        refreshing(false).also {
            Log.e(TAG, "error: ${error.cause}", error.cause)
            Snackbar.make(binding.root, R.string.load_todos_error, Snackbar.LENGTH_LONG)
        }
    }

    private fun setupBinding() {
        _binding = ActivityListTodosBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.fab.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    CreateTodoActivity::class.java
                )
            )
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.run {
            adapter = todosAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        binding.swipeLayout.setOnRefreshListener {
            presenter.execute(this::handleState)
        }
    }

    companion object {
        const val TAG = "list-todos"
    }

}
