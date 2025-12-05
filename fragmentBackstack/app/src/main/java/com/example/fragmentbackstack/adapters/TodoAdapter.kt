package com.example.fragmentbackstack.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentbackstack.databinding.ItemTodoBinding
import com.example.fragmentbackstack.viewmodels.TodoItem

/**
 * RecyclerView adapter for the MVVM Architecture demo.
 *
 * Uses ListAdapter with DiffUtil for efficient updates.
 */
class TodoAdapter(
    private val onToggle: (TodoItem) -> Unit,
    private val onDelete: (TodoItem) -> Unit
) : ListAdapter<TodoItem, TodoAdapter.TodoViewHolder>(TodoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TodoViewHolder(
        private val binding: ItemTodoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: TodoItem) {
            binding.apply {
                txtTodo.text = todo.text
                checkBox.isChecked = todo.isCompleted

                // Strike through completed items
                txtTodo.paintFlags = if (todo.isCompleted) {
                    txtTodo.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    txtTodo.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
                txtTodo.alpha = if (todo.isCompleted) 0.5f else 1f

                checkBox.setOnClickListener { onToggle(todo) }
                btnDelete.setOnClickListener { onDelete(todo) }
            }
        }
    }

    private class TodoDiffCallback : DiffUtil.ItemCallback<TodoItem>() {
        override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem == newItem
        }
    }
}

