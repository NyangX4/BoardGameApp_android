package com.example.boardgame.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgame.databinding.ThemeItemBinding

class ThemeAdpaters(var context : Context, var list : List<String>) : RecyclerView.Adapter<ThemeAdpaters.ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = ThemeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(itemHolder)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.onBind(list[position])
    }

    class ItemHolder(private val binding : ThemeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(theme : String) {
            binding.theme = theme
        }
    }
}