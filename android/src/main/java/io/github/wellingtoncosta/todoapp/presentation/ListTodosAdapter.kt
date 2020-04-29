package io.github.wellingtoncosta.todoapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.wellingtoncosta.todoapp.R
import io.github.wellingtoncosta.todoapp.databinding.ItemTodoBinding
import io.github.wellingtoncosta.todoapp.Todo

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
                status.text = itemView.context.getString(R.string.status, todo.status.capitalized())
                details.visibility = if (todo.description.isNullOrEmpty()) View.GONE else View.VISIBLE
                details.text = todo.description
            }
        }

    }

}