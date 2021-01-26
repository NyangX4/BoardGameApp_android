package com.example.boardgame.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgame.databinding.TagItemBinding

class FilterAdapters(var context : Context, var list : List<String>) : RecyclerView.Adapter<FilterAdapters.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = TagItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(itemHolder)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.onBind(list[position])
    }

    class ItemHolder(private val binding : TagItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(tag : String) {
            binding.tag = tag

            binding.tagItem.setOnClickListener {
                Toast.makeText(binding.root.context, tag, Toast.LENGTH_SHORT).show()
                binding.tagItem.isSelected = !binding.tagItem.isSelected
            }
        }
    }
}