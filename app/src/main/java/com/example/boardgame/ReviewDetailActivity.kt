package com.example.boardgame

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.boardgame.data.ReviewList
import com.example.boardgame.databinding.ActivityReviewDetailBinding
import com.example.boardgame.model.Review

class ReviewDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReviewDetailBinding
    private var reviewId: Int = 0
    private var review: Review? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_review_detail)

        reviewId = intent.getIntExtra("reviewId", 0)
        review = ReviewList.getReview(reviewId)
        binding.review = review

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
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.check_pwd_popup, null)
        val pwdEdit = dialogView.findViewById<EditText>(R.id.check_pwd_edit)

        builder.apply {
            setView(dialogView)
            setPositiveButton("확인", null)
            setNegativeButton("취소") { dialog, which ->
                // dialog 닫기
                dialog.dismiss()
            }
        }

        when (item.itemId) {
            R.id.review_detail_delete -> {
                val dialog = builder.create()
                dialog.show()

                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                    if (pwdEdit.text.toString() == review!!.pwd) {
                        dialog.dismiss()

                        // review 삭제
                        val checkBuilder = AlertDialog.Builder(this)
                        checkBuilder.apply {
                            setMessage("삭제하시겠습니까?")
                            setPositiveButton("예") { dialog, which ->
                                if (reviewId > 0) ReviewList.removeReview(reviewId)
                                finish()
                            }
                            setNegativeButton("아니오") { dialog, which ->
                                dialog.dismiss()
                            }
                        }
                        val checkDialog = checkBuilder.create()
                        checkDialog.show()
                    }
                    else {
                        Toast.makeText(applicationContext, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            R.id.review_detail_edit -> {
                val dialog = builder.create()
                dialog.show()

                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                    if (pwdEdit.text.toString() == review!!.pwd) {
                        dialog.dismiss()

                        val intent = Intent(binding.root.context, ReviewActivity::class.java)
                        intent.putExtra("reviewId", reviewId)
                        ContextCompat.startActivity(binding.root.context, intent, null)
                    }
                    else {
                        Toast.makeText(applicationContext, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
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

        // activity에 다시 들어올 때마다 data refresh함
        // 삭제된 review라면 activity 종료
        review = ReviewList.getReview(reviewId)

        if (review == null) finish()
        else binding.review = review
    }
}