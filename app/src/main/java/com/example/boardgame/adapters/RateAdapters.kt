package com.example.boardgame.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgame.databinding.RateItemBinding
import com.example.boardgame.model.Rates

class RateAdapters(var context: Context, var list: List<Rates>) : RecyclerView.Adapter<RateAdapters.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateAdapters.ItemHolder {
        val itemHolder = RateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RateAdapters.ItemHolder(itemHolder)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RateAdapters.ItemHolder, position: Int) {
        holder.onBind(list[position])
    }

    class ItemHolder(val binding: RateItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(rate : Rates) {
            binding.rate = rate
        }
    }
}