package com.example.boardgame.data

import com.example.boardgame.R
import com.example.boardgame.model.BoardGames

// 자동 formating : ctrl + alt + l

object DummyRepository {
    private val dummyDataList = listOf(
            BoardGames(1, R.drawable.terraforming_mars, "테라포밍 마스 (2016)", 2.93f, 146, 560),
            BoardGames(2, R.drawable.gloom_haven, "글룸헤이븐 (2016)", 3.25f, 47, 113),
            BoardGames(3, R.drawable.mage_knight, "메이지 나이트 (2011)", 4.43f, 74, 214),
            BoardGames(4, R.drawable.through_the_ages, "쓰루 디 에이지스(신판) (2015)", 4.14f, 54, 242),
            BoardGames(5, R.drawable.gaia_project, "가이아 프로젝트 (2017)", 4.22f, 52, 277),
            BoardGames(6, R.drawable.twilight_struggle, "황혼의 투쟁 (2005)", 3.76f, 125, 389),
            BoardGames(7, R.drawable.puerto_rico, "푸에르토 리코 (2002)", 2.89f, 137, 560)
    )

    fun getList() = dummyDataList
    fun getItem(id: Int): BoardGames? = dummyDataList.find { it.id == id }
}