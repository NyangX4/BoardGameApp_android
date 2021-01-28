package com.example.boardgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boardgame.adapters.GamesAdapters
import com.example.boardgame.data.DummyRepository
import com.example.boardgame.databinding.ActivityMainBinding
import com.example.boardgame.model.BoardGames

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

//    private var boardGameList : List<BoardGames> = DummyRepository.getList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // TODO : spanCount를 auto fit으로 바꿔주기
        binding.gamesRecyclerview.layoutManager = GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        binding.gamesRecyclerview.adapter = GamesAdapters(this, DummyRepository.getList())
        binding.gamesRecyclerview.setHasFixedSize(true)

        // toolbar 설정하기
        setSupportActionBar(binding.toolbar)

        binding.mainFilterApplyLayout.visibility = View.GONE
    }

    // toolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter_btn -> {
                val intent = Intent(binding.root.context, FilterActivity::class.java)
                startActivityForResult(intent, 1)
            }
            R.id.search_btn -> {
                val intent = Intent(binding.root.context, SearchActivity::class.java)
                ContextCompat.startActivity(binding.root.context, intent, null)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // get data from filter activity
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) run {
                val genreList : ArrayList<String> = data?.getStringArrayListExtra("genreList") as ArrayList<String>
                val themeList : ArrayList<String> = data.getStringArrayListExtra("themeList") as ArrayList<String>
                val numPeople : Int = data.getIntExtra("numPeople", 0)
                val levelMin : Int = data.getIntExtra("levelMin", 0)
                val levelMax : Int = data.getIntExtra("levelMax", 0)

                binding.mainFilterApplyLayout.visibility = View.VISIBLE
                binding.gamesRecyclerview.adapter = GamesAdapters(this,
                    DummyRepository.filtering(genreList, themeList, numPeople, levelMin, levelMax))

                binding.mainFilterXBtn.setOnClickListener {
                    binding.gamesRecyclerview.adapter = GamesAdapters(this, DummyRepository.getList())
                    binding.mainFilterApplyLayout.visibility = View.GONE
                }
            }
        }
    }

}
