package com.example.boardgame.data

import com.example.boardgame.R
import com.example.boardgame.model.BoardGames

// 자동 formating : ctrl + alt + l

object DummyRepository {

    private val dummyDataList = listOf(
        BoardGames(
            1, R.drawable.picture_terraforming_mars, "테라포밍 마스", 2016,
            listOf(
                TagList.getThemeId("SF"),
                TagList.getThemeId("경제"),
                TagList.getThemeId("산업/제조"),
                TagList.getThemeId("영토건설"),
                TagList.getThemeId("환경")
            ),
            1, 5, 90, 120 , 12,
            listOf(TagList.getGenreId("전략게임")), "https://bit.ly/3ndxE3U"
        ),

        BoardGames(
            2, R.drawable.picture_gloom_haven, "글룸헤이븐", 2016,
            listOf(
                TagList.getThemeId("모험"),
                TagList.getThemeId("경제"),
                TagList.getThemeId("판타지"),
                TagList.getThemeId("전투")
            ),
            1, 4, 60, 120, 12,
            listOf(TagList.getGenreId("테마게임")), "https://bit.ly/35mZ0i5"
        ),

        BoardGames(
            3, R.drawable.picture_mage_knight, "메이지 나이트", 2011,
            listOf(
                TagList.getThemeId("모험"),
                TagList.getThemeId("전투"),
                TagList.getThemeId("탐험"),
                TagList.getThemeId("판타지")
            ),
            1, 4, 60, 240, 14,
            listOf(TagList.getGenreId("전략게임"), TagList.getGenreId("테마게임")),
            "https://bit.ly/2JYPwlE"
        ),

        BoardGames(
            4, R.drawable.picture_through_the_ages, "쓰루 디 에이지스(신판)", 2015,
            listOf(TagList.getThemeId("문명"), TagList.getThemeId("경제")),
            2,4, 120,120, 14,
            listOf(TagList.getGenreId("전략게임")), "https://bit.ly/3pZAg7f"
        ),

        BoardGames(
            5, R.drawable.picture_gaia_project, "가이아 프로젝트", 2017,
            listOf(
                TagList.getThemeId("문명"),
                TagList.getThemeId("경제"),
                TagList.getThemeId("판타지"),
                TagList.getThemeId("테라포밍")
            ),
            1,4, 60,150, 12,
            listOf(TagList.getGenreId("전략게임")), "https://bit.ly/3s48d8u"
        ),

        BoardGames(
            6, R.drawable.picture_twilight_struggle, "황혼의 투쟁", 2005,
            listOf(TagList.getThemeId("현대전"), TagList.getThemeId("정치"), TagList.getThemeId("워게임")),
            2,2, 120,180, 13,
            listOf(TagList.getGenreId("전략게임"), TagList.getGenreId("워게임")),
            "https://bit.ly/2LfIXLU"
        ),

        BoardGames(
            7, R.drawable.picture_puerto_rico, "푸에르토 리코", 2002,
            listOf(TagList.getThemeId("도시건설"), TagList.getThemeId("경제"), TagList.getThemeId("농사")),
            3,5, 90,150, 12,
            listOf(TagList.getGenreId("전략게임")), "https://bit.ly/3s1qYtk"
        )
    )

    fun getList() = dummyDataList
    fun getItem(id: Int): BoardGames? = dummyDataList.find { it.id == id }

    // 단어를 기준으로 검색
    fun searchResultList(title: String?): MutableList<BoardGames> {
        var items: MutableList<BoardGames> = mutableListOf()

        for (item in dummyDataList) {
            if (item.gameTitle!!.startsWith(title!!)) {
                items.add(item)
                break
            }

            for (word in item.gameTitle!!.split(" ")) {
                if (word.startsWith(title)) {
                    items.add(item)
                    break
                }
            }
        }
        return items
    }
    fun getTitleList(): List<String?> = dummyDataList.map { it.gameTitle } // item의 title만 return
    fun getThemeTitleList(list : List<Int>) : List<String> = list.map { TagList.getThemeTitle(it) } // theme id -> title

    // 선택한 필터를 적용한 list return
    fun filtering(wantFiltering : List<BoardGames> = dummyDataList,
        genreList: ArrayList<String> = arrayListOf(), themeList: ArrayList<String> = arrayListOf(),
        numPeople: Int = -1, levelMin: Int = -1, levelMax: Int = 6) : List<BoardGames> {
        var filteredList = wantFiltering.toList()

        if (numPeople > -1) {
            filteredList = filteredList.filter { item -> item.peopleMin <= numPeople && numPeople <= item.peopleMax }
        }
        if (levelMin > -1 && levelMax < 6) {
            filteredList = filteredList.filter { item -> (levelMin.toFloat()) <= item.gameLevel!! && item.gameLevel!! <= (levelMax.toFloat()) }
        }

        var filteredMap = filteredList.map { it.id to 0 }.toMap()
        filteredMap = tagFiltering(filteredMap, filteredList, genreList, true)
        filteredMap = tagFiltering(filteredMap, filteredList, themeList, false)

        val sortedMap = filteredMap.toList()
            .filter { it.second > 0 }
            .sortedWith(compareByDescending { it.second }).toMap()
        val result : MutableList<BoardGames> = mutableListOf()
        for (id in sortedMap.keys) {
            result.add(filteredList.find { it.id == id }!!)
        }

        return result
    }

    private fun tagFiltering(nowMap: Map<Int, Int>, nowFiltered : List<BoardGames>,
                             filteringTagList: ArrayList<String>, isGenre: Boolean) : Map<Int, Int> {
        if (nowFiltered.isEmpty() || filteringTagList.isEmpty())
            return nowMap

        val tmpList = nowMap.toMutableMap()
        for (item in nowFiltered) {
            val cnt: Int? =
                if (isGenre) item.genreList?.count { TagList.getGenreTitle(it) in filteringTagList }
                else item.themeList?.count { TagList.getThemeTitle(it) in filteringTagList }

            tmpList[item.id] = tmpList[item.id]?.plus(cnt!!)!!
        }

        return tmpList
    }

    fun filteringCheckGood() = dummyDataList.filter { it.isGood }
}