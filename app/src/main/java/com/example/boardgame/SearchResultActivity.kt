package com.example.boardgame

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boardgame.adapters.GamesAdapters
import com.example.boardgame.data.DummyRepository
import com.example.boardgame.databinding.ActivitySearchresultBinding

class SearchResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchresultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_searchresult)

        var query : String? = null
        if (Intent.ACTION_SEARCH == intent.action) {
            query = intent.getStringExtra(SearchManager.QUERY)
        }

        setSupportActionBar(binding.resultToolbar)
        supportActionBar?.apply {
            title = query
            setDisplayHomeAsUpEnabled(true)
        }

        // TODO : auto fit으로 바꿔주기
        binding.resultRecyclerview.layoutManager = GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        binding.resultRecyclerview.adapter = GamesAdapters(this, DummyRepository.searchResultList(query))
        binding.resultRecyclerview.setHasFixedSize(true)
    }
}