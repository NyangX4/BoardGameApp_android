package com.example.boardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.boardgame.data.ReviewList
import com.example.boardgame.databinding.ActivityReviewDetailBinding

class ReviewDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityReviewDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_review_detail)

        val id = intent.getIntExtra("reviewId", 0)
        val data = ReviewList.getReview(id)
        binding.review = data

        // back button
        setSupportActionBar(binding.reviewDetailToolbar)
        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
        }
    }

    // toolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.review_detail_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.review_detail_delete -> Toast.makeText(this, "detail exit", Toast.LENGTH_SHORT).show()
            R.id.review_detail_edit -> Toast.makeText(this, "detail edit", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }

    // 뒤로가기 버튼
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}