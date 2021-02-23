package com.example.boardgame.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgame.databinding.TagItemBinding

class FilterAdapters(var context : Context, var list : List<String>) : RecyclerView.Adapter<FilterAdapters.ItemHolder>() {

    class ItemHolder(val binding : TagItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(tag : String) {
            binding.tag = tag
        }
    }

    private var selectedList : ArrayList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = TagItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(itemHolder)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.onBind(list[position])

        val tagItem = holder.binding.tagItem
        tagItem.setOnClickListener {
            tagItem.isSelected = !tagItem.isSelected
            onClickItem(list[position], tagItem.isSelected)
        }
    }

    private fun onClickItem(tag : String, isSelected : Boolean) {
        if (isSelected)
            selectedList.add(tag)
        else
            selectedList.remove(tag)
    }

    fun getSelectedList() = selectedList
    fun selectItem(holder : ItemHolder, position: Int, isSelected : Boolean) {
        holder.binding.tagItem.isSelected = isSelected
        onClickItem(list[position], isSelected)
    }
}