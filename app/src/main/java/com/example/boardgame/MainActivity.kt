package com.example.boardgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boardgame.adapters.GamesAdapters
import com.example.boardgame.data.DummyRepository
import com.example.boardgame.data.ReviewList
import com.example.boardgame.data.TagList
import com.example.boardgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle

    private var backBtnTime: Long = 0L
    private var isFiltered = false
    private var genreList: ArrayList<String>? = null
    private var themeList: ArrayList<String>? = null
    private var numPeople: Int? = null
    private var levelMin: Int? = null
    private var levelMax: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // TODO : spanCount를 auto fit으로 바꿔주기
        binding.gamesRecyclerview.layoutManager =
            GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        binding.gamesRecyclerview.adapter = GamesAdapters(this, DummyRepository.getList())
        binding.gamesRecyclerview.setHasFixedSize(true)

        // no filter list show
        binding.mainFilterApplyLayout.visibility = View.GONE
        binding.mainFilterXBtn.setOnClickListener {
            binding.toolbar.title = "보드게임"

            binding.gamesRecyclerview.adapter = GamesAdapters(this, DummyRepository.getList())
            binding.mainFilterApplyLayout.visibility = View.GONE

            isFiltered = false
        }

        // toolbar 설정하기
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // drawer menu
        setDrawerMenu()
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
            R.id.search_btn -> {
                val intent = Intent(binding.root.context, SearchActivity::class.java)
                ContextCompat.startActivity(binding.root.context, intent, null)
            }
        }

        // drawer menu
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
                genreList = data?.getStringArrayListExtra("genreList") as ArrayList<String>
                themeList = data.getStringArrayListExtra("themeList") as ArrayList<String>
                numPeople = data.getIntExtra("numPeople", 0)
                levelMin = data.getIntExtra("levelMin", 0)
                levelMax = data.getIntExtra("levelMax", 0)

                binding.mainFilterApplyLayout.visibility = View.VISIBLE
                binding.gamesRecyclerview.adapter = GamesAdapters(
                    this,
                    DummyRepository.filtering(
                        genreList = genreList!!, themeList = themeList!!, numPeople = numPeople!!,
                        levelMin = levelMin!!, levelMax = levelMax!!
                    )
                )
                isFiltered = true
            }
        }
    }

    // drawer menu
    private fun setDrawerMenu() {
        toggle =
            ActionBarDrawerToggle(this, binding.drawerLayout, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val menu = mutableListOf("전체", "좋아요 누른 게임")
        menu += TagList.getGenreList()
        binding.navListView.adapter = ArrayAdapter(this, R.layout.drawer_menu_list_item, menu)
        binding.navListView.setOnItemClickListener { parent, view, position, id ->
            binding.toolbar.title = menu[position]

            when (menu[position]) {
                "전체" -> {
                    binding.toolbar.title = "보드게임"
                    binding.gamesRecyclerview.adapter =
                        GamesAdapters(this, DummyRepository.getList())

                    // drawer menu 에서 선택된거 없애기
                    binding.navListView.adapter =
                        ArrayAdapter(this, R.layout.drawer_menu_list_item, menu)
                }
                "좋아요 누른 게임" -> {
                    binding.gamesRecyclerview.adapter =
                        GamesAdapters(this, DummyRepository.filteringCheckGood())
                }
                else -> {
                    binding.gamesRecyclerview.adapter = GamesAdapters(
                        this,
                        DummyRepository.filtering(genreList = arrayListOf(menu[position]))
                    )
                }
            }

            // close nav menu
            binding.drawerLayout.closeDrawer(Gravity.LEFT)
        }
    }

    // 두 번 눌러 앱 종료
    override fun onBackPressed() {
        val currTime = System.currentTimeMillis()
        val diffTime = currTime - backBtnTime

        if (diffTime in 0..2000) {
            super.onBackPressed()
        } else {
            backBtnTime = currTime
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()

        // activity에 다시 들어올 때마다 data refresh함
        binding.gamesRecyclerview.adapter = GamesAdapters(this, DummyRepository.getList())
    }
}
