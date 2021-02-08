package com.example.boardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.GridLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boardgame.adapters.RateAdapters
import com.example.boardgame.data.RateList
import com.example.boardgame.databinding.ActivityRateMoreBinding

class RateMoreActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRateMoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding 세팅
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rate_more)

        // back button
        setSupportActionBar(binding.rateMoreToolbar)
        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
        }

        val gameId = intent.getIntExtra("gameId", 0)
        // recycler view
        binding.rateMoreRecycler.layoutManager = GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false)
        binding.rateMoreRecycler.adapter = RateAdapters(this, RateList.getRateList(gameId))
        binding.rateMoreRecycler.setHasFixedSize(true)
    }


    // toolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.rate_more_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.rate_more_refresh -> Toast.makeText(this, "refresh", Toast.LENGTH_SHORT).show()
            R.id.rate_more_add -> Toast.makeText(this, "add", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }

    // 뒤로가기 버튼
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}