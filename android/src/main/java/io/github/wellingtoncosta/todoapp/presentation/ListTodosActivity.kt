package io.github.wellingtoncosta.todoapp.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.github.wellingtoncosta.todoapp.App
import io.github.wellingtoncosta.todoapp.R
import io.github.wellingtoncosta.todoapp.databinding.ActivityListTodosBinding

class ListTodosActivity : AppCompatActivity() {

    private var _binding: ActivityListTodosBinding? = null
    private val binding get() = _binding!!

    private val todosAdapter = ListTodosAdapter()

    private lateinit var todosViewModel: TodosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        todosViewModel = (applicationContext as App).todosViewModelFactory.create()

        setupBinding()

        setupRecyclerView()

        setupObservers()
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

        binding.swipeLayout.setOnRefreshListener { todosViewModel.load() }
    }

    private fun setupObservers() {
        todosViewModel.loading.observe(this, Observer {
            Log.d(TAG, "loading: $it")
            binding.swipeLayout.isRefreshing = it
        })

        todosViewModel.todos.observe(this, Observer {
            Log.d(TAG, "results: $it")
            todosAdapter.dataSource = it
        })

        todosViewModel.error.observe(this, Observer {
            Log.e(TAG, "error: $it", it)
            Snackbar.make(binding.root, R.string.load_todos_error, Snackbar.LENGTH_LONG)
        })
    }

    override fun onResume() {
        super.onResume()
        todosViewModel.load()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TAG = "list-todos"
    }

}
