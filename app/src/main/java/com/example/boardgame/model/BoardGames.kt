package com.example.boardgame.model

import com.example.boardgame.data.TagList

class BoardGames {
    var id: Int? = 0
    var gameImage: Int? = 0
    var gameTitle: String? = null
    var year : Int?  = 0
    var gameLevel: Float? = 0.0f
    var thumbCnt: Int? = 0
    var commentCnt: Int? = 0
    var themeList : List<Int>? = null
    var people: String? = null
    var playTime: String? = null
    var age : String? = null
    var genreList: List<Int>? = null
    var howToPlay: String? = null
    var rate: Float? = 0.0f
    var similarList: List<Int>? = null

    constructor(id: Int?, gameImage: Int?, gameTitle: String?, year : Int?, gameLevel: Float?, thumbCnt: Int?, commentCnt: Int?,
                themeList : List<Int>?, people: String?, playTime: String?, age : String?, genreList: List<Int>?, howToPlay: String?, rate: Float?, similarList: List<Int>?) {
        this.id = id
        this.gameImage = gameImage
        this.gameTitle = gameTitle
        this.year = year
        this.gameLevel = gameLevel
        this.thumbCnt = thumbCnt
        this.commentCnt = commentCnt
        this.themeList = themeList
        this.people = people
        this.playTime = playTime
        this.age = age
        this.genreList = genreList
        this.howToPlay = howToPlay
        this.rate = rate
        this.similarList = similarList
    }

    fun yearToString() = year.toString() + "년"
    fun gameLevelToString() = gameLevel.toString() + " / 5"
    fun rateToString() = rate.toString() + " / 10"
    fun genreToString() : String {
        var items : MutableList<String> = mutableListOf()
        for (item in genreList!!) {
            items.add(TagList.getGenreTitle(item))
        }
        return items.joinToString()
    }
}