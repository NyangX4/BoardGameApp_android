package com.example.boardgame.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgame.ReviewDetailActivity
import com.example.boardgame.databinding.ReviewItemBinding
import com.example.boardgame.model.Review

class ReviewAdapters(var context: Context, var list: List<Review>, private val isThumb : Boolean) : RecyclerView.Adapter<ReviewAdapters.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = ReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(itemHolder)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val review = list[position]

        holder.onBind(review)
        holder.binding.reviewItemContent.text = if (isThumb) review.contentThumbnail() else review.content
    }

    class ItemHolder(val binding: ReviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(review : Review) {
            binding.review = review

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, ReviewDetailActivity::class.java)
                intent.putExtra("reviewId", review.id)
                ContextCompat.startActivity(binding.root.context, intent, null)
            }
        }
    }
}