package com.example.boardgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgame.adapters.GamesAdapters
import com.example.boardgame.adapters.ThemesAdpaters
import com.example.boardgame.model.BoardGames

class DetailActivity : AppCompatActivity() {
    private var themeRecyclerView : RecyclerView? = null
    private var themeGridLayoutManager : GridLayoutManager? = null
    private var themeArrayList : ArrayList<String>? = null
    private var themeAdapter : ThemesAdpaters? = null

    private var similarRecyclerView : RecyclerView? = null
    private var similarGridLayoutManager : GridLayoutManager? = null
    private var similarArrayList : ArrayList<BoardGames>? = null
    private var similarAdapter : GamesAdapters? = null

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

        // theme recyclerView
        themeRecyclerView = findViewById<RecyclerView>(R.id.detail_theme_recyclerView)
        // TODO : spanCount를 auto fit으로 바꿔주기
        themeGridLayoutManager = GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false)
        themeRecyclerView?.layoutManager = themeGridLayoutManager
        themeRecyclerView?.setHasFixedSize(true)
        themeArrayList = setThemeDataList()
        themeAdapter = ThemesAdpaters(this, themeArrayList!!)
        themeRecyclerView?.adapter = themeAdapter

        // similar recyclerView
        similarRecyclerView = findViewById<RecyclerView>(R.id.detail_similar_recyclerView)
        similarGridLayoutManager = GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false)
        similarRecyclerView?.layoutManager = similarGridLayoutManager
        similarRecyclerView?.setHasFixedSize(true)
        similarArrayList = setSimilarDataList()
        similarAdapter = GamesAdapters(this, similarArrayList!!)
        similarRecyclerView?.adapter = similarAdapter
    }

    private fun setThemeDataList() : ArrayList<String> {
        var items : ArrayList<String> = ArrayList()

        items.add("SF")
        items.add("경제")
        items.add("산업/제조")
        items.add("영토건설")
        items.add("환경")

        return items
    }

    private fun setSimilarDataList() : ArrayList<BoardGames> {
        var items : ArrayList<BoardGames> = ArrayList()

        items.add(BoardGames(R.drawable.twilight_struggle, "황혼의 투쟁 (2005)", 3.76f, 125, 389))
        items.add(BoardGames(R.drawable.mage_knight, "메이지 나이트 (2011)", 4.43f, 74, 214))

        return items
    }
}