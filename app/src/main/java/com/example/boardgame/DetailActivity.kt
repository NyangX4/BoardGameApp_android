package com.example.boardgame

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boardgame.adapters.GamesAdapters
import com.example.boardgame.adapters.ThemesAdpaters
import com.example.boardgame.model.BoardGames
import com.example.boardgame.data.DummyRepository
import com.example.boardgame.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding 세팅
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val id = intent.getIntExtra("id", 0)
        val data = DummyRepository.getItem(id)
        binding.boardGame = data

        // back button
        setSupportActionBar(binding.detailToolbar)
        supportActionBar?.apply {
            // show back button on toolbar
            setDisplayHomeAsUpEnabled(true)
            // icon 바꾸기
            setHomeAsUpIndicator(R.drawable.ic_home)
        }

        // theme recyclerView
        // TODO : spanCount를 auto fit으로 바꿔주기
        binding.detailThemeRecyclerView.layoutManager = GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false)
        binding.detailThemeRecyclerView.adapter = ThemesAdpaters(this, data?.themeList!!)
        binding.detailThemeRecyclerView.setHasFixedSize(true)

        // similar recyclerView
        binding.detailSimilarRecyclerView.layoutManager = GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false)
        binding.detailSimilarRecyclerView.adapter = GamesAdapters(this, setSimilarDataList(data.similarList))
        binding.detailSimilarRecyclerView.setHasFixedSize(true)
    }

    private fun setSimilarDataList(list : List<Int>?): MutableList<BoardGames> {
        var items: MutableList<BoardGames> = mutableListOf()

        list?.forEach { id -> DummyRepository.getItem(id)?.let { items.add(it) } }

        return items
    }
}