package com.example.boardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boardgame.adapters.GamesAdapters
import com.example.boardgame.data.DummyRepository
import com.example.boardgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // TODO : spanCount를 auto fit으로 바꿔주기
        binding.gamesRecyclerview.layoutManager = GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        binding.gamesRecyclerview.adapter = GamesAdapters(this, DummyRepository.getList())
        binding.gamesRecyclerview.setHasFixedSize(true)
    }
}
