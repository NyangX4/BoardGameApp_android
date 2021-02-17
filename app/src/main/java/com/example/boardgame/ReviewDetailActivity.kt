package com.example.boardgame

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
    private var reviewId: Int? = null
    private var review: Review? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_review_detail)

        reviewId = intent.getIntExtra("reviewId", 0)
        review = ReviewList.getReview(reviewId!!)
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
        when (item.itemId) {
            R.id.review_detail_delete -> {
                Toast.makeText(this, "detail exit", Toast.LENGTH_SHORT).show()

            }
            R.id.review_detail_edit -> {
//                Toast.makeText(this, "detail edit", Toast.LENGTH_SHORT).show()

                val builder = AlertDialog.Builder(this)
                val dialogView = layoutInflater.inflate(R.layout.check_pwd_popup, null)
                val pwdEdit = dialogView.findViewById<EditText>(R.id.check_pwd_edit)

                builder.apply {
                    setView(dialogView)
                    setPositiveButton("확인") { dialog, which ->
                        if (pwdEdit.text.toString() == review!!.pwd) {
                            val intent = Intent(binding.root.context, ReviewActivity::class.java)
                            intent.putExtra("reviewId", reviewId)
                            ContextCompat.startActivity(binding.root.context, intent, null)
                        } else {
                            // TODO : 비밀번호 틀렸다는 warning 보내기
//                            Log.i("false!", pwdEdit.text.toString())
                        }
                    }
                    setNegativeButton("취소") { dialog, which -> }

                    show()
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
        review = ReviewList.getReview(reviewId!!)
        binding.review = review
    }

//    private fun checkPwd(): Boolean {
//        val builder = AlertDialog.Builder(this)
//        val dialogView = layoutInflater.inflate(R.layout.check_pwd_popup, null)
//        val pwdEdit = dialogView.findViewById<EditText>(R.id.check_pwd_edit)
//        var result = false
//
//        builder.apply {
//            setView(dialogView)
//            setPositiveButton("확인") { dialog, which ->
//                if (pwdEdit.text.toString() == review!!.pwd) {
//                    result = true
//
//                    Log.i("true!", pwdEdit.text.toString())
//                } else {
//                    // TODO : 비밀번호 틀렸다는 warning 보내기
//                    Log.i("false!", pwdEdit.text.toString())
//                }
//            }
//            setNegativeButton("취소") { dialog, which -> }
//
//            show()
//        }
//
//        return result
//    }
}