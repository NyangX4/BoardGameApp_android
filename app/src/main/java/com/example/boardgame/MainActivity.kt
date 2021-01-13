package com.example.boardgame

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
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

        // toolbar 설정하기
        setSupportActionBar(binding.toolbar)
    }

    // toolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter_btn -> Toast.makeText(this, "filter", Toast.LENGTH_LONG).show()
            R.id.search_btn -> {
                val intent = Intent(binding.root.context, SearchActivity::class.java)
                ContextCompat.startActivity(binding.root.context, intent, null)
            }
        }

        return super.onOptionsItemSelected(item)
    }


}
