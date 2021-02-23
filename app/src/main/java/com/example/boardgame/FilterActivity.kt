package com.example.boardgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgame.adapters.FilterAdapters
import com.example.boardgame.data.TagList
import com.example.boardgame.databinding.ActivityFilterBinding

class FilterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilterBinding

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
        // select all button
        binding.filterGenreSelectAll.setOnClickListener {
            setSelectAll(binding.filterGenreSelectAll, binding.filterGenreRecyclerview, genreAdapter, !binding.filterGenreSelectAll.isSelected)
        }
        binding.filterThemeSelectAll.setOnClickListener {
            setSelectAll(binding.filterThemeSelectAll, binding.filterThemeRecyclerview, themeAdapter, !binding.filterThemeSelectAll.isSelected)
        }

        // spinner
        // TODO : 조건 없는 경우 추가하기
        val levelItems: List<Int> = listOf(0, 1, 2, 3, 4, 5)
        binding.filterLevelSpinnerMin.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, levelItems)
        binding.filterLevelSpinnerMax.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, levelItems)
        binding.filterLevelSpinnerMax.setSelection(levelItems.size - 1)

        // apply button
        binding.filterApplyBtn.setOnClickListener {
            val min = binding.filterLevelSpinnerMin.selectedItem as Int
            val max = binding.filterLevelSpinnerMax.selectedItem as Int

            // TODO : warning 문구 바꾸기
            if (binding.filterPeopleEdit.text.toString().isEmpty()) {
                // 인원 수 입력이 없을 때
                Toast.makeText(this, "인원 수 warning", Toast.LENGTH_SHORT).show()
            }
            else if (min > max) {
                Toast.makeText(this, "난이도 warning", Toast.LENGTH_SHORT).show()
            }
            else {
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

        // 전체 뷰가 다 그려진 후 setPreviousFilter()가 실행되기 위해
        // viewTreeObserver 사용
        binding.filterRootLayout.viewTreeObserver.addOnGlobalLayoutListener (object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                // apply previous filter
                if (intent.getBooleanExtra("isFiltered", false)) {
                    setPreviousFilter()
                }

                // 1회성을 위해 Listener 제거
                // 그렇지 않으면 setPreviousFilter()가 계속 실행됨.
                binding.filterRootLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    // 뒤로가기 버튼
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setRecyclerView() {
        // genre recyclerView
        // TODO : spanCount를 auto fit으로 바꿔주기
        binding.filterGenreRecyclerview.layoutManager =
            GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        binding.filterGenreRecyclerview.adapter = genreAdapter
        binding.filterGenreRecyclerview.setHasFixedSize(true)

        // theme recyclerView
        // TODO : spanCount를 auto fit으로 바꿔주기
        binding.filterThemeRecyclerview.layoutManager =
            GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        binding.filterThemeRecyclerview.adapter = themeAdapter
        binding.filterThemeRecyclerview.setHasFixedSize(true)
    }

    private fun setPeopleBtn() {
        val editText = binding.filterPeopleEdit
        editText.setOnClickListener {
            editText.setSelection(0, editText.text.toString().length)
        }

        // plus button
        binding.filterPeoplePlus.setOnClickListener {
            if (editText.text.toString() == "") editText.setText("1")
            else
                editText.setText((Integer.parseInt(editText.text.toString()) + 1).toString())
        }
        // remove button
        binding.filterPeopleRemove.setOnClickListener {
            if (editText.text.toString() == "") editText.setText("0")
            else if (Integer.parseInt(editText.text.toString()) > 0)
                editText.setText((Integer.parseInt(editText.text.toString()) - 1).toString())
        }
    }

    private fun setSelectAll(allBtn : ImageButton, recyclerView: RecyclerView, adapters: FilterAdapters, isSelected : Boolean) {
        allBtn.isSelected = isSelected

        for (i in 0 until recyclerView.childCount) {
            val holder : FilterAdapters.ItemHolder =
                recyclerView.getChildViewHolder(recyclerView.getChildAt(i)) as FilterAdapters.ItemHolder

            adapters.selectItem(holder, i, isSelected)
        }
    }

    private fun setPreviousFilter() {
        val genreList = intent.getStringArrayListExtra("genreList")
        val themeList = intent.getStringArrayListExtra("themeList")
        val numPeople = intent.getIntExtra("numPeople", -1)
        val levelMin = intent.getIntExtra("levelMin", -1)
        val levelMax = intent.getIntExtra("levelMax", -1)


        // set genreList
        if (genreList!!.size == TagList.getGenreList().size) {
            setSelectAll(binding.filterGenreSelectAll, binding.filterGenreRecyclerview, genreAdapter, true)
        }
        else {
            val allList = TagList.getGenreList()

            for (genre in genreList) {
                val idx = allList.indexOf(genre)
                val holder : FilterAdapters.ItemHolder =
                    binding.filterGenreRecyclerview.getChildViewHolder(binding.filterGenreRecyclerview.getChildAt(idx)) as FilterAdapters.ItemHolder

                genreAdapter.selectItem(holder, idx, true)
            }
        }

        // set themeList
        if (themeList!!.size == TagList.getThemeList().size) {
            setSelectAll(binding.filterThemeSelectAll, binding.filterThemeRecyclerview, themeAdapter, true)
        }
        else {
            val allList = TagList.getThemeList()

            for (theme in themeList) {
                val idx = allList.indexOf(theme)
                val holder : FilterAdapters.ItemHolder =
                    binding.filterThemeRecyclerview.getChildViewHolder(binding.filterThemeRecyclerview.getChildAt(idx)) as FilterAdapters.ItemHolder

                themeAdapter.selectItem(holder, idx, true)
            }
        }

        // set numPeople, levelMin, levelMax
        if (numPeople != -1 && levelMin != -1 && levelMax != -1) {
            binding.filterPeopleEdit.setText(numPeople.toString())
            binding.filterLevelSpinnerMin.setSelection(levelMin)
            binding.filterLevelSpinnerMax.setSelection(levelMax)
        }

    }
}