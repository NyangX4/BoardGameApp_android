package com.example.boardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.boardgame.data.ReviewList
import com.example.boardgame.databinding.ActivityReviewBinding
import com.example.boardgame.model.Review

class ReviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReviewBinding
    private var gameId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_review)
        gameId = intent.getIntExtra("gameId", 0)

        setSupportActionBar(binding.reviewToolbar)
        supportActionBar?.apply {
            title = ""
            // show back button on toolbar
            setDisplayHomeAsUpEnabled(true)
        }

        // seekbar
        binding.reviewSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.reviewLevelShow.text = (progress.toFloat() / 2).toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    // toolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.review_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.review_delete -> Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show()
            R.id.review_save -> Toast.makeText(this, "save", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)

        builder.apply {
            setMessage("저장하시겠습니까?")
            setPositiveButton("예") { dialog, which ->
                // TODO : 새로운 리뷰 저장하기
                ReviewList.addReview(Review(
                    ReviewList.nowId++,
                    gameId,
                    binding.reviewEditName.text.toString(),
                    binding.reviewPwd.text.toString(),
                    System.currentTimeMillis(),
                    binding.reviewRatebar.rating,
                    binding.reviewLevelShow.text.toString().toFloat(),
                    binding.reviewContentEdit.text.toString()
                ))
                finish()
            }
            setNegativeButton("아니오") { dialog, which ->
                finish()
            }
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }

    // 뒤로가기 버튼
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}