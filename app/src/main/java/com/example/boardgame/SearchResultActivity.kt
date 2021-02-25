package com.example.boardgame

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boardgame.adapters.GamesAdapters
import com.example.boardgame.data.DummyRepository
import com.example.boardgame.databinding.ActivitySearchresultBinding
import com.example.boardgame.model.BoardGames

class SearchResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchresultBinding

    private var result : List<BoardGames> = listOf()
    private var isFiltered = false
    private var genreList : ArrayList<String>? = null
    private var themeList : ArrayList<String>? = null
    private var numPeople : Int? = null
    private var levelMin : Int? = null
    private var levelMax : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_searchresult)

        var query : String? = null
        if (Intent.ACTION_SEARCH == intent.action) {
            query = intent.getStringExtra(SearchManager.QUERY)
        }
        result = DummyRepository.searchResultList(query)

        setSupportActionBar(binding.resultToolbar)
        supportActionBar?.apply {
            title = query
            setDisplayHomeAsUpEnabled(true)
        }

        // filter btn
        binding.resultFilterBtn.setOnClickListener {
            val intent = Intent(binding.root.context, FilterActivity::class.java)
            if (isFiltered) {
                intent.putExtra("isFiltered", true)
                intent.putExtra("genreList", genreList)
                intent.putExtra("themeList", themeList)
                intent.putExtra("numPeople", numPeople)
                intent.putExtra("levelMin", levelMin)
                intent.putExtra("levelMax", levelMax)
            }
            startActivityForResult(intent, 1)
        }

        // no filter list show
        binding.resultFilterXLayout.visibility = View.GONE
        binding.resultFilterXBtn.setOnClickListener {
            binding.resultRecyclerview.adapter = GamesAdapters(this, result)
            binding.resultFilterXLayout.visibility = View.GONE

            isFiltered = false
        }

        // TODO : auto fit으로 바꿔주기
        binding.resultRecyclerview.layoutManager = GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        binding.resultRecyclerview.adapter = GamesAdapters(this, result)
        binding.resultRecyclerview.setHasFixedSize(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // get data from filter activity
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) run {
                genreList = data?.getStringArrayListExtra("genreList") as ArrayList<String>
                themeList = data.getStringArrayListExtra("themeList") as ArrayList<String>
                numPeople = data.getIntExtra("numPeople", 0)
                levelMin = data.getIntExtra("levelMin", 0)
                levelMax = data.getIntExtra("levelMax", 0)

                binding.resultFilterXLayout.visibility = View.VISIBLE
                binding.resultRecyclerview.adapter = GamesAdapters(this,
                    DummyRepository.filtering(result, genreList!!, themeList!!, numPeople!!, levelMin!!, levelMax!!))

                isFiltered = true
            }
        }
    }
}