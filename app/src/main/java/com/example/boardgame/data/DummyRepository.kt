package com.example.boardgame.data

import com.example.boardgame.R
import com.example.boardgame.model.BoardGames

// 자동 formating : ctrl + alt + l

object DummyRepository {
    private val dummyDataList = listOf(
        BoardGames(
            1, R.drawable.terraforming_mars, "테라포밍 마스 (2016)", 2.93f, 146, 560,
            listOf("SF", "경제", "산업/제조", "영토건설", "환경"), "1~5인 (3~4인 추천)", "90~120분", "12세 이상",
            "전략게임", "https://bit.ly/3ndxE3U", 8.42f, listOf(1, 2)
        ),

        BoardGames(
            2, R.drawable.gloom_haven, "글룸헤이븐 (2016)", 3.25f, 47, 113,
            listOf("모험", "경제", "판타지", "전투"), "1~4명 (3인 추천)", "60~120분", "12세 이상",
            "테마게임", "https://bit.ly/35mZ0i5", 7.43f, listOf(1, 5, 7)
        ),

        BoardGames(
            3, R.drawable.mage_knight, "메이지 나이트 (2011)", 4.43f, 74, 214,
            listOf("모험", "전투", "탐험", "판타지"), "1~4명 (1~3명, 1~2인 추천)", "150분", "14세 이상",
            "전략/테마게임", "https://bit.ly/2JYPwlE", 5.32f, listOf(4)
        ),

        BoardGames(
            4, R.drawable.through_the_ages, "쓰루 디 에이지스(신판) (2015)", 4.14f, 54, 242,
            listOf("문명", "경제"), "2~4명", "240분", "12세 이상",
            "전략게임", "https://bit.ly/3pZAg7f", 6.48f, listOf()
        ),

        BoardGames(
            5, R.drawable.gaia_project, "가이아 프로젝트 (2017)", 4.22f, 52, 277,
            listOf("문명", "경제", "판타지", "테라포밍"), "1~4명 (4인 추천)", "60~150분", "12세 이상",
            "전략게임", "https://bit.ly/3s48d8u", 7.23f, listOf(4, 8, 1, 3)
        ),

        BoardGames(
            6, R.drawable.twilight_struggle, "황혼의 투쟁 (2005)", 3.76f, 125, 389,
            listOf("현대전", "정치", "워게임"), "2명", "180분", "13세 이상",
            "전략/워게임", "https://bit.ly/2LfIXLU", 5.89f, listOf()
        ),

        BoardGames(
            7, R.drawable.puerto_rico, "푸에르토 리코 (2002)", 2.89f, 137, 560,
            listOf("도시건설", "경제", "농사"), "2~5명 (4인 추천)", "90~150분", "12세 이상",
            "전략게임", "https://bit.ly/3s1qYtk", 6.77f, listOf()
        )
    )

    fun getList() = dummyDataList
    fun getItem(id: Int): BoardGames? = dummyDataList.find { it.id == id }
    fun getId(title : String) : Int? = dummyDataList.find { it.gameTitle == title }?.id
    fun getTitleList() : MutableList<String?> {
        var titles = mutableListOf<String?>()
        for(item in dummyDataList) {
            titles.add(item.gameTitle)
        }

        return titles
    }
}