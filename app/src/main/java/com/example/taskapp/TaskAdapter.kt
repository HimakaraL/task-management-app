package com.example.taskapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_list.view.cBox
import kotlinx.android.synthetic.main.todo_list.view.todoName

class TaskAdapter (
    private val Tasks: MutableList<Task>
) : RecyclerView.Adapter<TaskAdapter.TaskViewholder>() {

    class TaskViewholder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewholder {
        return TaskViewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.todo_list,
                parent,
                false
            )
        )
    }

    fun deleteTask() {
        Tasks.removeAll { task ->
            task.isDone
        }
        notifyDataSetChanged()
    }


    fun addTask(task: Task) {
        Tasks.add(task)
        notifyItemInserted(Tasks.size - 1)
    }




    private fun changeValues(todoName: TextView, isDone: Boolean)
    {
        if(isDone){
            todoName.paintFlags = todoName.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            todoName.paintFlags = todoName.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    //Assigning task details
    override fun onBindViewHolder(holder: TaskViewholder, position: Int) {
        val currentTask = Tasks[position]
        holder.itemView.apply {
            todoName.text = currentTask.name
            cBox.isChecked = currentTask.isDone
            changeValues(todoName, currentTask.isDone)
            cBox.setOnCheckedChangeListener { _, isChecked ->
                changeValues(todoName, isChecked)
                currentTask.isDone = !currentTask.isDone
            }
        }
    }


    //getSize
    override fun getItemCount(): Int {
        return Tasks.size
    }
}