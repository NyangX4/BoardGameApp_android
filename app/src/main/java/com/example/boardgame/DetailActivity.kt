package com.example.boardgame

import android.content.Intent
import android.net.Uri
import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boardgame.adapters.GamesAdapters
import com.example.boardgame.adapters.ThemeAdpaters
import com.example.boardgame.model.BoardGames
import com.example.boardgame.data.DummyRepository
import com.example.boardgame.data.TagList
import com.example.boardgame.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    private lateinit var data : BoardGames
    private var gameId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding 세팅
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        gameId = intent.getIntExtra("id", 0)
        data = DummyRepository.getItem(gameId)!!
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
        setSimilarRecyclerView()

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

        // review more activity
        binding.detailRateMoreBtn.setOnClickListener {
            val intent = Intent(binding.root.context, ReviewMoreActivity::class.java)
            intent.putExtra("gameId", gameId)
            ContextCompat.startActivity(binding.root.context, intent, null)
        }

        // review activity
        binding.detailRateBtn.setOnClickListener {
            val intent = Intent(binding.root.context, ReviewActivity::class.java)
            intent.putExtra("gameId", gameId)
            ContextCompat.startActivity(binding.root.context, intent, null)
        }
    }

    // 뒤로가기 버튼
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setSimilarRecyclerView() {
        val arr : ArrayList<String> = arrayListOf()
        for (theme in data.themeList!!) {
            arr.add(TagList.getThemeTitle(theme))
        }

        val result = DummyRepository.filtering(themeList = arr).filter { it.id != gameId }

        if (result.isNotEmpty()) {
            binding.detailSimilarLayout.visibility = View.VISIBLE

            // TODO : spanCount를 auto fit으로 바꿔주기
            binding.detailSimilarRecyclerView.layoutManager = GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false)
            binding.detailSimilarRecyclerView.adapter = GamesAdapters(this, result)
            binding.detailSimilarRecyclerView.setHasFixedSize(true)
        }
    }

    fun howToPlayOnClick(view : View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.howToPlay))
        startActivity(intent)
    }
}
