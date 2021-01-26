package com.example.boardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.boardgame.databinding.ActivityRateMoreBinding

class RateMoreActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRateMoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_filter_more)
        // binding 세팅
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rate_more)

        // back button
        setSupportActionBar(binding.rateMoreToolbar)
        supportActionBar?.apply {
            // show back button on toolbar
            setDisplayHomeAsUpEnabled(true)
        }
    }
}