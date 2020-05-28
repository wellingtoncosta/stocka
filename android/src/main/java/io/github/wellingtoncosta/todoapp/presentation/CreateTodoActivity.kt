package io.github.wellingtoncosta.todoapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import io.github.wellingtoncosta.todoapp.App
import io.github.wellingtoncosta.todoapp.R
import io.github.wellingtoncosta.todoapp.databinding.ActivityCreateTodoBinding
import io.github.wellingtoncosta.todoapp.domain.Todo
import io.github.wellingtoncosta.todoapp.presentation.create.*

class CreateTodoActivity : AppCompatActivity() {

    private var _binding: ActivityCreateTodoBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: CreateNewTodoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = (applicationContext as App).createNewTodoPresenter

        setupBinding()

        setupToolbar()

        setupSaveButton()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        presenter.destroy()
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

    private fun validateFields(): Boolean {
        return (!binding.titleEditText.text.isNullOrEmpty()).also {
            binding.titleInput.isErrorEnabled = !it
            binding.titleInput.error = if (!it) getString(R.string.title_required) else null
        }
    }

    private fun setupSaveButton() {
        binding.saveButton.setOnClickListener {
            if (validateFields()) {
                val todo = Todo(
                    title = binding.titleEditText.text!!.toString(),
                    description = binding.detailsEditText.text.toString()
                )

                binding.root.hideKeyboard()
                presenter.execute(todo, this::handleState)
            }
        }
    }

    private fun handleState(state: CreateNewTodoState) {
        when (state) {
            is Loading -> saving(true)
            is Success -> handleSuccess()
            is Error -> handleError(state)
        }
    }

    private fun saving(value: Boolean) {
        binding.titleInput.visibility = if (value) View.GONE else View.VISIBLE
        binding.detailsEditText.visibility = if (value) View.GONE else View.VISIBLE
        binding.saveButton.visibility = if (value) View.GONE else View.VISIBLE
        binding.progress.visibility = if (value) View.VISIBLE else View.GONE
    }

    private fun handleSuccess() {
        saving(false).also {
            finish()
        }
    }

    private fun handleError(error: Error) {
        saving(false).also {
            Log.e(TAG, "error: ${error.cause}", error.cause)
            Snackbar.make(binding.root, R.string.create_new_todo_failure, Snackbar.LENGTH_LONG).show()
        }
    }

    companion object {
        const val TAG = "create-new-todo"
    }

}
