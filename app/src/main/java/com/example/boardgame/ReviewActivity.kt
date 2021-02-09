package com.example.boardgame

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.boardgame.databinding.ActivityReviewBinding

class ReviewActivity : AppCompatActivity() {
    private lateinit var binding : ActivityReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_review)

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
            setPositiveButton("예", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(applicationContext, "예", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            })
            setNegativeButton("아니오", DialogInterface.OnClickListener { dialog, which ->
                finish()
            })
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