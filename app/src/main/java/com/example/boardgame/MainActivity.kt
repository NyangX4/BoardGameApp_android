package com.example.boardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgame.adapters.GamesAdapters
import com.example.boardgame.data.DummyRepository

// TODO : data binding 해주기
class MainActivity : AppCompatActivity() {
    private var recylerView: RecyclerView? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var gamesAdpaters: GamesAdapters? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recylerView = findViewById(R.id.games_recyclerview)
        // TODO : spanCount를 auto fit으로 바꿔주기
        gridLayoutManager = GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        recylerView?.layoutManager = gridLayoutManager
        recylerView?.setHasFixedSize(true)
        gamesAdpaters = GamesAdapters(this, DummyRepository.getList())
        recylerView?.adapter = gamesAdpaters
    }
}
