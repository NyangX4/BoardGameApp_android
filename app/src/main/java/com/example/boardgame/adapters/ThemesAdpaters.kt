package com.example.boardgame.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgame.R

class ThemesAdpaters(var context : Context, var arrayList : ArrayList<String>) : RecyclerView.Adapter<ThemesAdpaters.ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.theme_item, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        var theme_item : String = arrayList[position]

        holder.theme_text.text = theme_item
    }

    class ItemHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var theme_text = itemView.findViewById<TextView>(R.id.theme_item)
    }


}