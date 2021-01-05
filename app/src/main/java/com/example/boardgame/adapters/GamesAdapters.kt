package com.example.boardgame.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgame.R
import com.example.boardgame.model.BoardGames

class GamesAdapters(var context: Context, var arrayList: ArrayList<BoardGames>) : RecyclerView.Adapter<GamesAdapters.ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        var boardGames : BoardGames = arrayList.get(position)

        holder.image.setImageResource(boardGames.gameImage!!)
        holder.title.text = boardGames.gameTitle
        holder.level.text = boardGames.gameLevel.toString() + " / 5"
        holder.thumbCnt.text = boardGames.thumbCnt.toString()
        holder.commentCnt.text = boardGames.commentCnt.toString()

        holder.title.setOnClickListener {
            Toast.makeText(context, boardGames.gameTitle, Toast.LENGTH_LONG).show()
        }
    }

    class ItemHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.game_imageView)
        var title = itemView.findViewById<TextView>(R.id.title_textView)
        var level = itemView.findViewById<TextView>(R.id.level_textView)
        var thumbCnt = itemView.findViewById<TextView>(R.id.thumb_textView)
        var commentCnt = itemView.findViewById<TextView>(R.id.comment_textView)
    }

}