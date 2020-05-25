package io.github.wellingtoncosta.todoapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import io.github.wellingtoncosta.todoapp.App
import io.github.wellingtoncosta.todoapp.R
import io.github.wellingtoncosta.todoapp.databinding.ActivityCreateTodoBinding
import io.github.wellingtoncosta.todoapp.entity.Todo

class CreateTodoActivity : AppCompatActivity() {

    private var _binding: ActivityCreateTodoBinding? = null
    private val binding get() = _binding!!

    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        todoViewModel = (applicationContext as App).todoViewModeFactory.create()

        setupBinding()

        setupToolbar()

        setupSaveButton()

        setupObservers()
    }

    private fun setupBinding() {
        _binding = ActivityCreateTodoBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    private fun setupToolbar() {
        with(binding.toolbar) {
            navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_arrow_back)
            setNavigationOnClickListener { finish() }
        }
    }

    private fun setupSaveButton() {
        binding.saveButton.setOnClickListener {
            if (validateFields()) {
                val todo = Todo(
                    title = binding.titleEditText.text!!.toString(),
                    description = binding.detailsEditText.text.toString()
                )

                Log.d(TAG, "Saving new todo: $todo")

                todoViewModel.save(todo)
            }
        }
    }

    private fun validateFields(): Boolean {
        return (!binding.titleEditText.text.isNullOrEmpty()).also {
            binding.titleInput.isErrorEnabled = !it
            binding.titleInput.error = if (!it) getString(R.string.title_required) else null
        }
    }

    private fun setupObservers() {
        todoViewModel.saving.observe(this, Observer {
            Log.d(TAG, "loading: $it")
            binding.titleInput.visibility = if (it) View.GONE else View.VISIBLE
            binding.detailsEditText.visibility = if (it) View.GONE else View.VISIBLE
            binding.saveButton.visibility = if (it) View.GONE else View.VISIBLE
        })

        todoViewModel.saved.observe(this, Observer {
            Log.d(TAG, "saved: $it")
            if (it) { finish() }
        })

        todoViewModel.error.observe(this, Observer {
            Log.e(TAG, "error: $it", it)
            Snackbar.make(binding.root, R.string.create_new_todo_failure, Snackbar.LENGTH_LONG)
        })
    }

    companion object {
        const val TAG = "create-new-todo"
    }

}
