package com.example.boardgame.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgame.DetailActivity
import com.example.boardgame.databinding.CardItemBinding
import com.example.boardgame.model.BoardGames

class GamesAdapters(var context: Context, var list: List<BoardGames>) :
    RecyclerView.Adapter<GamesAdapters.ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(itemHolder)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val data = list[position]
        holder.onBind(data)
    }

    class ItemHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(boardGame: BoardGames) {
            binding.boardGame = boardGame

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailActivity::class.java)
                intent.putExtra("id", boardGame.id)
                ContextCompat.startActivity(binding.root.context, intent, null)
            }
        }
    }

}