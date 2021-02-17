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
import com.example.boardgame.model.Review

class ReviewMoreActivity : AppCompatActivity() {
    private lateinit var binding : ActivityReviewMoreBinding
    private lateinit var reviewAdapter : ReviewAdapters
    private var gameId = 0

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

        gameId = intent.getIntExtra("gameId", 0)
        reviewAdapter = ReviewAdapters(this, ReviewList.getReviewList(gameId))
        // recycler view
        binding.reviewMoreRecycler.layoutManager = LinearLayoutManager(this)
        binding.reviewMoreRecycler.adapter = reviewAdapter
        binding.reviewMoreRecycler.setHasFixedSize(true)
    }


    // toolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.review_more_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.review_more_refresh -> {
                reviewAdapter = ReviewAdapters(this, ReviewList.getReviewList(gameId))
                binding.reviewMoreRecycler.adapter = reviewAdapter
            }
            R.id.review_more_add -> {
                val intent = Intent(binding.root.context, ReviewActivity::class.java)
                intent.putExtra("gameId", gameId)
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

    override fun onResume() {
        super.onResume()

        // TODO : code check
        // activity에 다시 들어올 때마다 adapter refresh함
        reviewAdapter = ReviewAdapters(this, ReviewList.getReviewList(gameId))
        binding.reviewMoreRecycler.adapter = reviewAdapter
    }
}