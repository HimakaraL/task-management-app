package com.example.taskapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences("TaskPreferences", Context.MODE_PRIVATE)
        val savedTasks = sharedPreferences.getString("tasks", null)
        val tasksListType = object : TypeToken<MutableList<Task>>() {}.type
        val tasks: MutableList<Task> = Gson().fromJson(savedTasks, tasksListType) ?: mutableListOf()

        taskAdapter = TaskAdapter(tasks)

        rVTaskItems.adapter = taskAdapter
        rVTaskItems.layoutManager = LinearLayoutManager(this)

        bAddTask.setOnClickListener {
            val name = eTTaskName.text.toString()
            if (name.isNotEmpty()) {
                val task = Task(name)
                taskAdapter.addTask(task)
                eTTaskName.text.clear()
                saveTasks()
            }
        }

        bDddTask.setOnClickListener {
            taskAdapter.deleteTask()
            saveTasks()
        }
    }

    private fun saveTasks() {
        val tasksJson = Gson().toJson(taskAdapter.tasks)
        sharedPreferences.edit().putString("tasks", tasksJson).apply()
    }
}
