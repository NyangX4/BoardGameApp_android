package com.example.boardgame

import android.content.Intent
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
//    private var gameId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_review)
//        gameId = intent.getIntExtra("gameId", 0)

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
            R.id.review_save -> {
                val intent = Intent()
                intent.putExtra("name", binding.reviewEditName.text.toString())
                intent.putExtra("pwd", binding.reviewPwd.text.toString())
                intent.putExtra("rating", binding.reviewRatebar.rating)
                intent.putExtra("gameLevel", binding.reviewLevelShow.text.toString().toFloat())
                intent.putExtra("content", binding.reviewContentEdit.text.toString())
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)

        builder.apply {
            setMessage("저장하시겠습니까?")
            setPositiveButton("예") { dialog, which ->
                val intent = Intent()
                intent.putExtra("name", binding.reviewEditName.text.toString())
                intent.putExtra("pwd", binding.reviewPwd.text.toString())
                intent.putExtra("rating", binding.reviewRatebar.rating)
                intent.putExtra("gameLevel", binding.reviewLevelShow.text.toString().toFloat())
                intent.putExtra("content", binding.reviewContentEdit.text.toString())
                setResult(RESULT_OK, intent)
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