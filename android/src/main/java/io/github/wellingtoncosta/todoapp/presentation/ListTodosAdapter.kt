package io.github.wellingtoncosta.todoapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.wellingtoncosta.todoapp.R
import io.github.wellingtoncosta.todoapp.databinding.ItemTodoBinding
import io.github.wellingtoncosta.todoapp.shared.domain.entity.Todo
import java.util.*

class ListTodosAdapter : RecyclerView.Adapter<ListTodosAdapter.ViewHolder>() {

    var dataSource: List<Todo> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSource[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemTodoBinding.bind(view)

        fun bind(todo: Todo) {
            with(binding) {
                title.text = todo.title
                details.text = todo.details
                status.text = getFormattedStatus(todo)
            }
        }

        private fun getFormattedStatus(todo: Todo): String {
            val status = todo.status.toString().toLowerCase(Locale.getDefault())
            val firstLetter = status.substring(0, 1).toUpperCase(Locale.getDefault())
            val capitalized = "$firstLetter${status.substring(1)}"
            return itemView.context.getString(R.string.status, capitalized)
        }

    }

}