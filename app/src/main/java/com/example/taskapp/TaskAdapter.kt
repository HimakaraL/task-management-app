package com.example.taskapp

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_list.view.*

class TaskAdapter(val tasks: MutableList<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.todo_list,
                parent,
                false
            )
        )
    }

    fun deleteTask() {
        tasks.removeAll { task ->
            task.isDone
        }
        notifyDataSetChanged()
    }

    fun addTask(task: Task) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }
    
    //Cut the Done Tasks
    private fun changeValues(todoName: TextView, isDone: Boolean) {
        if (isDone) {
            todoName.paintFlags = todoName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            todoName.paintFlags = todoName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = tasks[position]
        holder.itemView.apply {
            todoName.text = currentTask.name
            cBox.isChecked = currentTask.isDone
            changeValues(todoName, currentTask.isDone)
            cBox.setOnCheckedChangeListener { _, isChecked ->
                changeValues(todoName, isChecked)
                currentTask.isDone = isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}
