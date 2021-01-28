package com.example.boardgame

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.boardgame.adapters.FilterAdapters
import com.example.boardgame.adapters.ThemeAdpaters
import com.example.boardgame.data.DummyRepository
import com.example.boardgame.data.TagList
import com.example.boardgame.databinding.ActivityFilterBinding

class FilterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFilterBinding

    private lateinit var genreAdapter: FilterAdapters
    private lateinit var themeAdapter: FilterAdapters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding 세팅
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter)
        genreAdapter = FilterAdapters(this, TagList.getGenreList())
        themeAdapter = FilterAdapters(this, TagList.getThemeList())

        setSupportActionBar(binding.filterToolbar)
        supportActionBar?.apply {
            title = ""
            // show back button on toolbar
            setDisplayHomeAsUpEnabled(true)
        }

        setRecyclerView()
        setPeopleBtn()

        // spinner
        // TODO : 조건 없는 경우 추가하기
        val levelItems : List<Int> = listOf(0,1,2,3,4,5)
        val levelAdapter : ArrayAdapter<Int?> = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, levelItems)
        binding.filterLevelSpinnerMin.adapter = levelAdapter
        binding.filterLevelSpinnerMax.adapter = levelAdapter

        // apply button
        binding.filterApplyBtn.setOnClickListener {
            val min = binding.filterLevelSpinnerMin.selectedItem as Int
            val max = binding.filterLevelSpinnerMax.selectedItem as Int

            if (min != 0 && max != 0) {
                if (min > max) {
                    // TODO : warning 문구 바꾸기
                    Log.i("에러 메시지", "들어옴!")
                    Toast.makeText(applicationContext, "인원 수 warning", Toast.LENGTH_SHORT).show()

                    //TODO : 예외처리 추가하기
                    // 1. 인원 수 입력이 없을 때

                    return@setOnClickListener
                }
            }

            val intent: Intent = Intent()
            intent.putExtra("genreList", genreAdapter.getSelectedList())
            intent.putExtra("themeList", themeAdapter.getSelectedList())
            intent.putExtra("numPeople", Integer.parseInt(binding.filterPeopleEdit.text.toString()))
            intent.putExtra("levelMin", min)
            intent.putExtra("levelMax", max)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun setRecyclerView() {
        // genre recyclerView
        // TODO : spanCount를 auto fit으로 바꿔주기
        binding.filterGenreRecyclerview.layoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        binding.filterGenreRecyclerview.adapter = genreAdapter
        binding.filterGenreRecyclerview.setHasFixedSize(true)

        // theme recyclerView
        // TODO : spanCount를 auto fit으로 바꿔주기
        binding.filterThemeRecyclerview.layoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        binding.filterThemeRecyclerview.adapter = themeAdapter
        binding.filterThemeRecyclerview.setHasFixedSize(true)
    }

    private fun setPeopleBtn() {
        val editText = binding.filterPeopleEdit
        editText.setOnClickListener{
            editText.setSelection(0, editText.text.toString().length)
        }

        // plus button
        binding.filterPeoplePlus.setOnClickListener {
            if (editText.text.toString() == "") editText.setText("1")
            else
                editText.setText((Integer.parseInt(editText.text.toString())+1).toString())
        }
        // remove button
        binding.filterPeopleRemove.setOnClickListener {
            if (editText.text.toString() == "") editText.setText("0")
            else if (Integer.parseInt(editText.text.toString()) > 0)
                editText.setText((Integer.parseInt(editText.text.toString())-1).toString())
        }
    }

    // 뒤로가기 버튼
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}