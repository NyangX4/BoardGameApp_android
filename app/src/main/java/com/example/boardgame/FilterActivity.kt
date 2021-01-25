package com.example.boardgame

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding 세팅
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter)

        setSupportActionBar(binding.filterToolbar)
        supportActionBar?.apply {
            title = ""
            // show back button on toolbar
            setDisplayHomeAsUpEnabled(true)
        }

        setRecyclerView()
        setPeopleBtn()

        // spinner
        val levelItems : List<Int> = listOf(0,1,2,3,4,5)
        val levelAdapter : ArrayAdapter<Int?> = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, levelItems)
        binding.filterLevelSpinnerMin.adapter = levelAdapter
        binding.filterLevelSpinnerMax.adapter = levelAdapter

        // apply button
        binding.filterApplyBtn.setOnClickListener {
            Toast.makeText(this, binding.filterLevelSpinnerMin.selectedItem.toString(), Toast.LENGTH_SHORT).show()
        }

    }

    private fun setRecyclerView() {
        // genre recyclerView
        // TODO : spanCount를 auto fit으로 바꿔주기
        binding.filterGenreRecyclerview.layoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        binding.filterGenreRecyclerview.adapter = FilterAdapters(this, TagList.getGenreList())
        binding.filterGenreRecyclerview.setHasFixedSize(true)

        // theme recyclerView
        // TODO : spanCount를 auto fit으로 바꿔주기
        binding.filterThemeRecyclerview.layoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        binding.filterThemeRecyclerview.adapter = FilterAdapters(this, TagList.getThemeList())
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