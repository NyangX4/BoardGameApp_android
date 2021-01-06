package com.example.boardgame.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgame.DetailActivity
import com.example.boardgame.R
import com.example.boardgame.model.BoardGames

class GamesAdapters(var context: Context, var arrayList: ArrayList<BoardGames>) : RecyclerView.Adapter<GamesAdapters.ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        var boardGames : BoardGames = arrayList[position]

        holder.image.setImageResource(boardGames.gameImage!!)
        holder.title.text = boardGames.gameTitle
        holder.level.text = boardGames.gameLevel.toString() + " / 5"
        holder.thumbCnt.text = boardGames.thumbCnt.toString()
        holder.commentCnt.text = boardGames.commentCnt.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView?.context, DetailActivity::class.java)
            intent.putExtra("image", boardGames.gameImage!!)
            intent.putExtra("title", boardGames.gameTitle)
            intent.putExtra("level", boardGames.gameLevel)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
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