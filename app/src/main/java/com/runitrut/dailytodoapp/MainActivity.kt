package com.runitrut.dailytodoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.runitrut.dailytodoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // instantiates the RV TodoAdapter class into lateinit var names todoAdapter
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        // view-binding initiated
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoAdapter = TodoAdapter(mutableListOf())

        binding.todoRV.adapter = todoAdapter
        binding.todoRV.layoutManager = LinearLayoutManager(this)

        binding.addBTN.setOnClickListener {
            val todoTitle = binding.todoET.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                binding.todoET.text.clear()
            }
        }
        binding.deleteBTN.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }


}