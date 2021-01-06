package com.example.boardgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val game_img = intent.getIntExtra("image", -1)
        val game_title = intent.getStringExtra("title")
        val game_level = intent.getFloatExtra("level", 0.0f)


        val game_imageView = findViewById<ImageView>(R.id.detail_game_imageView)
        game_imageView.setImageResource(game_img)
        val detail_title_tv = findViewById<TextView>(R.id.detail_title_tv).apply {
            text = game_title
        }
        val detail_level_tv = findViewById<TextView>(R.id.detail_level_tv).apply {
            text = game_level.toString() + " / 5"
        }
    }
}