package com.example.boardgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
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
    private lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // TODO : spanCount를 auto fit으로 바꿔주기
        binding.gamesRecyclerview.layoutManager = GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        binding.gamesRecyclerview.adapter = GamesAdapters(this, DummyRepository.getList())
        binding.gamesRecyclerview.setHasFixedSize(true)

        // toolbar 설정하기
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // drawable menu
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_strategy -> Toast.makeText(this, "전략게임", Toast.LENGTH_SHORT).show()
                R.id.nav_theme -> Toast.makeText(this, "테마게임", Toast.LENGTH_SHORT).show()
                R.id.nav_wargames -> Toast.makeText(this, "워게임", Toast.LENGTH_SHORT).show()
            }
            true
        }

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

        // navigation menu
        if (toggle.onOptionsItemSelected(item)) {
            return true
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

                // no filter list show
                binding.mainFilterXBtn.setOnClickListener {
                    binding.gamesRecyclerview.adapter = GamesAdapters(this, DummyRepository.getList())
                    binding.mainFilterApplyLayout.visibility = View.GONE
                }
            }
        }
    }

}
