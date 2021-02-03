package com.example.boardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.boardgame.databinding.ActivityMainBinding
import com.example.boardgame.databinding.ActivityRateBinding

class RateActivity : AppCompatActivity() {
    // lateinit: 늦은 초기화
    // Null로 처리하지 않고 프로퍼티의 초기화를 미룸 = 프로퍼티의 선언과 동시에 초기화 X
    // (https://kkangsnote.tistory.com/67)
    private lateinit var binding: ActivityRateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rate)

        setSupportActionBar(binding.rateToolbar)

        // supportActionBar is nullable
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }
}