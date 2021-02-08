package com.example.boardgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boardgame.adapters.ReviewAdapters
import com.example.boardgame.data.ReviewList
import com.example.boardgame.databinding.ActivityReviewMoreBinding

class ReviewMoreActivity : AppCompatActivity() {
    private lateinit var binding : ActivityReviewMoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding 세팅
        binding = DataBindingUtil.setContentView(this, R.layout.activity_review_more)

        // back button
        setSupportActionBar(binding.reviewMoreToolbar)
        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
        }

        val gameId = intent.getIntExtra("gameId", 0)
        // recycler view
        binding.reviewMoreRecycler.layoutManager = LinearLayoutManager(this)
        binding.reviewMoreRecycler.adapter = ReviewAdapters(this, ReviewList.getReviewList(gameId))
        binding.reviewMoreRecycler.setHasFixedSize(true)
    }


    // toolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.review_more_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.review_more_refresh -> Toast.makeText(this, "refresh", Toast.LENGTH_SHORT).show()
            R.id.review_more_add -> {
                val intent = Intent(binding.root.context, ReviewActivity::class.java)
                ContextCompat.startActivity(binding.root.context, intent, null)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    // 뒤로가기 버튼
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}