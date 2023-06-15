package com.example.task_manager.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.task_manager.databinding.ItemTaskBinding
import com.example.task_manager.model.Task

class TaskAdapter : Adapter<TaskAdapter.TaskViewHolder>() {

    private val list = arrayListOf<Task>()



    fun setTask(task:Task){
        list.add(0,task)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount():Int = list.size


    override fun onBindViewHolder(holder:TaskViewHolder, position:Int){
        holder.bind(list[position])
    }


    inner class TaskViewHolder(private val binding: ItemTaskBinding):ViewHolder(binding.root){
        fun bind(task: Task)= with(binding) {
            tvTitle.text = task.title
            tvDesc.text = task.title
        }

    }


}