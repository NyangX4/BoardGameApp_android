package com.example.boardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgame.adapters.GamesAdapters
import com.example.boardgame.model.BoardGames
class MainActivity : AppCompatActivity() {
    private var recylerView : RecyclerView ? = null
    private var gridLayoutManager : GridLayoutManager ? = null
    private var arrayList : ArrayList<BoardGames> ? = null
    private var gamesAdpaters : GamesAdapters ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recylerView = findViewById(R.id.games_recyclerView)
        // TODO : spanCount를 auto fit으로 바꿔주기
        gridLayoutManager = GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        recylerView?.layoutManager = gridLayoutManager
        recylerView?.setHasFixedSize(true)
        arrayList = setDataList()
        gamesAdpaters = GamesAdapters(this, arrayList!!)
        recylerView?.adapter = gamesAdpaters
    }

    private fun setDataList() : ArrayList<BoardGames> {
        var items : ArrayList<BoardGames> = ArrayList()

        items.add(BoardGames(R.drawable.terraforming_mars, "테라포밍 마스 (2016)", 2.93f, 146, 560))
        items.add(BoardGames(R.drawable.gloom_haven, "글룸헤이븐 (2016)", 3.25f, 47, 113))
        items.add(BoardGames(R.drawable.mage_knight, "메이지 나이트 (2011)", 4.43f, 74, 214))
        items.add(BoardGames(R.drawable.through_the_ages, "쓰루 디 에이지스(신판) (2015)", 4.14f, 54, 242))
        items.add(BoardGames(R.drawable.gaia_project, "가이아 프로젝트 (2017)", 4.22f, 52, 277))
        items.add(BoardGames(R.drawable.twilight_struggle, "황혼의 투쟁 (2005)", 3.76f, 125, 389))
        items.add(BoardGames(R.drawable.puerto_rico, "푸에르토 리코 (2002)", 2.89f, 137, 560))

        return items
    }
}

