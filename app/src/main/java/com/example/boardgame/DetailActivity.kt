package com.example.boardgame

import android.content.Intent
import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boardgame.adapters.GamesAdapters
import com.example.boardgame.adapters.ThemeAdpaters
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
        }

        // theme recyclerView
        // TODO : spanCount를 auto fit으로 바꿔주기
        binding.detailThemeRecyclerView.layoutManager = GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false)
        binding.detailThemeRecyclerView.adapter = ThemeAdpaters(this, DummyRepository.getThemeTitleList(data?.themeList!!))
        binding.detailThemeRecyclerView.setHasFixedSize(true)

        // similar recyclerView
        binding.detailSimilarRecyclerView.layoutManager = GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false)
        binding.detailSimilarRecyclerView.adapter = GamesAdapters(this, setSimilarDataList(data.similarList))
        binding.detailSimilarRecyclerView.setHasFixedSize(true)

        // search activity
        binding.detailSearchBtn.setOnClickListener {
            val intent = Intent(binding.root.context, SearchActivity::class.java)
            ContextCompat.startActivity(binding.root.context, intent, null)
        }

        // when clicked thumb btn
        binding.detailThumb.setOnClickListener {
            binding.detailThumb.isSelected = !binding.detailThumb.isSelected
            // TODO : activity가 종료되어도 그대로 남아있기
        }
    }

    private fun setSimilarDataList(list : List<Int>?): MutableList<BoardGames> {
        var items: MutableList<BoardGames> = mutableListOf()

        list?.forEach { id -> DummyRepository.getItem(id)?.let { items.add(it) } }

        return items
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
