package com.example.task_manager.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.task_manager.R
import com.example.task_manager.databinding.ItemBoardBinding

class BoardAdapter(private val click: Result) : RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {
    private val imgList = listOf(R.raw.note_first, R.raw.note_anim, R.raw.note_anim2)
    private val titleList = listOf("title 1", "title 2" , "title 3")
    private val descList = listOf("desc 1", "desc 2" , "desc 3")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BoardViewHolder (
        ItemBoardBinding.inflate(LayoutInflater.from(parent.context),parent , false)
    )



    override fun getItemCount() = imgList.size


    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.onBind(position)
        holder.binding.itemBtn.setOnClickListener {
            click.onClick()
        }
    }


    inner class BoardViewHolder(val binding : ItemBoardBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(position: Int){
            binding.itemImgBoard.setAnimation(imgList[position])
            binding.itemTvTitle.text = titleList[position]
            binding.itemTvDesc.text = descList[position]




            if (position == 2) {
                binding.itemBtn.visibility = View.VISIBLE
            } else {
                binding.itemBtn.visibility = View.GONE
            }
        }
    }

    interface Result {
        fun onClick()
    }

}
