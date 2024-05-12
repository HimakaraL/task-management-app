package com.example.taskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        taskAdapter = TaskAdapter(mutableListOf())

        rVTaskItems.adapter = taskAdapter
        rVTaskItems.layoutManager = LinearLayoutManager(this)

        bAddTask.setOnClickListener {
            val name = eTTaskName.text.toString()
            if (name.isNotEmpty()) {
                val task = Task(name)
                taskAdapter.addTask(task)
                eTTaskName.text.clear()
            }
        }

        bDddTask.setOnClickListener {
            taskAdapter.deleteTask()
        }
    }
}