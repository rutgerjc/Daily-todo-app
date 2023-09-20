package com.runitrut.dailytodoapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.TextureView
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.runitrut.dailytodoapp.databinding.ItemTodoBinding

// This TodoAdapter class hold the logic to create and bind functionality to each individual Recycler item
class TodoAdapter(
    // Pass in parameter that extended mutableList from the data classTodo
    private val todos:MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){
    // The ViewHolder will hold the specific details and blueprint for each RecyclerView view, via..
    // creating the ViewHolder
    // The parameter of the VH is a reference to the actual view.
    // It extends RV.VH() and its argument is the parameter of the VH class, which is again the reference to the actual view.
    // Lastly; extended the TodoAdapter class with RV.Adapter<TodoAdapter>.TodoVH>()
    inner class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTodoBinding.inflate(layoutInflater,parent,false)
        return TodoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  todos.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.binding.apply {
            todoTitleTV.text = curTodo.title
            doneCB.isChecked = curTodo.isChecked
            toggleStrikeThrough(todoTitleTV, curTodo.isChecked)
            doneCB.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(todoTitleTV,isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }

    fun deleteDoneTodos(){
        todos.removeAll {todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isCheck: Boolean){
        if (isCheck){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

}