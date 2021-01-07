package com.example.boardgame

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
import com.example.boardgame.data.DummyRepository

class DetailActivity : AppCompatActivity() {
    private var themeRecyclerView: RecyclerView? = null
    private var themeGridLayoutManager: GridLayoutManager? = null
    private var themeList: List<String>? = null
    private var themeAdapter: ThemesAdpaters? = null

    private var similarRecyclerView: RecyclerView? = null
    private var similarGridLayoutManager: GridLayoutManager? = null
    private var similarList: MutableList<BoardGames>? = null
    private var similarAdapter: GamesAdapters? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getIntExtra("id", 0)
        val data = DummyRepository.getItem(id)

        val gameImageView = findViewById<ImageView>(R.id.detail_game_imageView)
        gameImageView.setImageResource(data?.gameImage!!)
        val detailTitleTv = findViewById<TextView>(R.id.detail_title_tv).apply {
            text = data?.gameTitle
        }
        val detailLevelTv = findViewById<TextView>(R.id.detail_level_tv).apply {
            text = data?.gameLevel.toString() + " / 5"
        }

        // theme recyclerView
        themeRecyclerView = findViewById<RecyclerView>(R.id.detail_theme_recyclerView)
        // TODO : spanCount를 auto fit으로 바꿔주기
        themeGridLayoutManager = GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false)
        themeRecyclerView?.layoutManager = themeGridLayoutManager
        themeRecyclerView?.setHasFixedSize(true)
        themeList = setThemeDataList()
        themeAdapter = ThemesAdpaters(this, themeList!!)
        themeRecyclerView?.adapter = themeAdapter

        // similar recyclerView
        similarRecyclerView = findViewById<RecyclerView>(R.id.detail_similar_recyclerView)
        similarGridLayoutManager = GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false)
        similarRecyclerView?.layoutManager = similarGridLayoutManager
        similarRecyclerView?.setHasFixedSize(true)
        similarList = setSimilarDataList()
        similarAdapter = GamesAdapters(this, similarList!!)
        similarRecyclerView?.adapter = similarAdapter
    }

    private fun setThemeDataList(): List<String> {
        return listOf("SF", "경제", "산업/제조", "영토건설", "환경")
    }

    private fun setSimilarDataList(): MutableList<BoardGames> {
        var items: MutableList<BoardGames> = mutableListOf()

        // 위의 코드를 아래와 같이 한 줄로 표현할 수 있음.
        DummyRepository.getItem(3)?.let { items.add(it) }
        DummyRepository.getItem(4)?.let { items.add(it) }

        return items
    }
}