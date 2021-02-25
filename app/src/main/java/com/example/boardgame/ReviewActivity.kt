package com.example.boardgame

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
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
    private var reviewId = 0

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


        // edit review
        reviewId = intent.getIntExtra("reviewId", 0)
        if (reviewId > 0) {
            val review = ReviewList.getReview(reviewId)

            binding.reviewRatebar.rating = review!!.rate
            binding.reviewEditName.setText(review.name)
            binding.reviewPwd.setText(review.pwd)
            binding.reviewSeekbar.progress = (review.gameLevel * 2).toInt()
            binding.reviewLevelShow.text = review.gameLevel.toString()
            binding.reviewContentEdit.setText(review.content)
        }

        // 배경 터치 시 키보드 없애기
        binding.reviewLinearLayout.setOnClickListener {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            imm.hideSoftInputFromWindow(binding.reviewEditName.windowToken, 0)
            imm.hideSoftInputFromWindow(binding.reviewPwd.windowToken, 0)
            imm.hideSoftInputFromWindow(binding.reviewContentEdit.windowToken, 0)
        }
    }

    // toolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.review_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.review_delete -> {
                // review 삭제
                val checkBuilder = AlertDialog.Builder(this)
                checkBuilder.apply {
                    setMessage("작성 중인 리뷰를 삭제하시겠습니까?")
                    setPositiveButton("예") { dialog, which ->
                        if (reviewId > 0 && gameId == 0) ReviewList.removeReview(reviewId)

                        Toast.makeText(applicationContext, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    setNegativeButton("아니오") { dialog, which ->
                        dialog.dismiss()
                    }
                }
                val checkDialog = checkBuilder.create()
                checkDialog.show()
            }
            R.id.review_save -> {
                if (!checkEmptyText()) return super.onOptionsItemSelected(item)

                if (gameId > 0 && reviewId == 0) {
                    // 새로운 review 추가
                    ReviewList.addReview(
                        Review(
                            ReviewList.nowId++,
                            gameId,
                            binding.reviewEditName.text.toString(),
                            binding.reviewPwd.text.toString(),
                            System.currentTimeMillis(),
                            binding.reviewRatebar.rating,
                            binding.reviewLevelShow.text.toString().toFloat(),
                            binding.reviewContentEdit.text.toString()
                        )
                    )

                    Toast.makeText(applicationContext, "저장되었습니다.", Toast.LENGTH_SHORT).show()
                }
                else if (reviewId > 0 && gameId == 0) {
                    // review 수정
                    ReviewList.getReview(reviewId)?.editReview(
                        binding.reviewEditName.text.toString(),
                        binding.reviewPwd.text.toString(),
                        binding.reviewRatebar.rating,
                        binding.reviewLevelShow.text.toString().toFloat(),
                        binding.reviewContentEdit.text.toString()
                    )

                    Toast.makeText(applicationContext, "수정되었습니다.", Toast.LENGTH_SHORT).show()
                }
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (binding.reviewContentEdit.text.toString().isEmpty()) {
            finish()
            return
        }

        val builder = AlertDialog.Builder(this)

        builder.apply {
            setMessage("작성 중인 리뷰를 저장하시겠습니까?")
            setPositiveButton("예") { dialog, which ->
                if (!checkEmptyText()) {
                    dialog.dismiss()
                }
                else if (gameId > 0 && reviewId == 0) {
                    // 새로운 review 추가
                    ReviewList.addReview(
                        Review(
                            ReviewList.nowId++,
                            gameId,
                            binding.reviewEditName.text.toString(),
                            binding.reviewPwd.text.toString(),
                            System.currentTimeMillis(),
                            binding.reviewRatebar.rating,
                            binding.reviewLevelShow.text.toString().toFloat(),
                            binding.reviewContentEdit.text.toString()
                        )
                    )

                    Toast.makeText(applicationContext, "저장되었습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
                else if (reviewId > 0 && gameId == 0) {
                    // review 수정
                    ReviewList.getReview(reviewId)?.editReview(
                        binding.reviewEditName.text.toString(),
                        binding.reviewPwd.text.toString(),
                        binding.reviewRatebar.rating,
                        binding.reviewLevelShow.text.toString().toFloat(),
                        binding.reviewContentEdit.text.toString()
                    )

                    Toast.makeText(applicationContext, "수정되었습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            setNegativeButton("아니오") { dialog, which ->
                finish()
            }
        }

        val dialog = builder.create()
        dialog.show()
    }

    // 뒤로가기 버튼
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun checkEmptyText() : Boolean {
        if (binding.reviewEditName.text.toString().isEmpty()) {
            Toast.makeText(applicationContext, "이름을 작성해주세요", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.reviewPwd.text.toString().isEmpty()) {
            Toast.makeText(applicationContext, "비밀번호를 작성해주세요", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}