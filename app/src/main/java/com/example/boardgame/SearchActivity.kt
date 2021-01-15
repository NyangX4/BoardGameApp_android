package com.example.boardgame

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.boardgame.data.DummyRepository
import com.example.boardgame.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        // toolbar
        setSupportActionBar(binding.searchToolbar)
        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
        }

        // search
        val searchListView = binding.searchListview
        val searchView = binding.searchSearchview

        val adapter : ArrayAdapter<String?> = ArrayAdapter(this, android.R.layout.simple_list_item_1, DummyRepository.getTitleList())
        searchListView.adapter = adapter
        searchListView.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id ->
            searchView.setQuery(parent?.getItemAtPosition(position).toString(), false) // searchview에 선택된 값이 올라가기
        }

        searchView.isIconified = false // searchView가 있는 화면에 들어가면 자동 focusing이 됨
        val searchManager : SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(ComponentName(this, SearchResultActivity::class.java)))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchSearchview.clearFocus() // searchView의 focusing이 사라짐
                finish() // submit한 후 activity 끝냄
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}