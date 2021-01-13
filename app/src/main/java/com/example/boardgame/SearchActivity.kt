package com.example.boardgame

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        val titles = DummyRepository.getTitleList()
        val adapter : ArrayAdapter<String?> = ArrayAdapter(this, android.R.layout.simple_list_item_1, titles)
        searchListView.adapter = adapter
        searchListView.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id ->
            val intent = Intent(binding.root.context, DetailActivity::class.java)
            intent.putExtra("id", DummyRepository.getId(parent?.getItemAtPosition(position).toString()))
            ContextCompat.startActivity(binding.root.context, intent, null)
            finish()
        }

        binding.searchSearchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchSearchview.clearFocus()
                if (titles.contains(query)) adapter.filter.filter(query)
                else Toast.makeText(applicationContext, "Item not found", Toast.LENGTH_LONG).show()

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