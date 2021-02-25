package com.example.boardgame.model

import com.example.boardgame.data.ReviewList
import com.example.boardgame.data.TagList

class BoardGames {
    var id: Int = 0
    var gameImage: Int = 0
    var gameTitle: String? = null
    var year: Int = 0
    var gameLevel: Float? = 0.0f
    var thumbCnt: Int = 0
    var commentCnt: Int = 0
    var themeList: List<Int>? = null
    var peopleMin: Int = -1
    var peopleMax: Int = -1
    var playTimeMin: Int = -1
    var playTimeMax: Int = -1
    var age: Int = -1
    var genreList: List<Int>? = null
    var howToPlay: String? = null
    var rate: Float = 0.0f

    // TODO : code check
    var isGood = false

    constructor(
        id: Int,
        gameImage: Int,
        gameTitle: String,
        year: Int,
        themeList: List<Int>,
        peopleMin: Int,
        peopleMax: Int,
        playTimeMin: Int,
        playTimeMax: Int,
        age: Int,
        genreList: List<Int>,
        howToPlay: String
    ) {
        this.id = id
        this.gameImage = gameImage
        this.gameTitle = gameTitle
        this.year = year
//        this.gameLevel = gameLevel
//        this.thumbCnt = thumbCnt
//        this.commentCnt = commentCnt
        this.themeList = themeList
        this.peopleMin = peopleMin
        this.peopleMax = peopleMax
        this.playTimeMin = playTimeMin
        this.playTimeMax = playTimeMax
        this.age = age
        this.genreList = genreList
        this.howToPlay = howToPlay
//        this.rate = rate
    }

    fun yearToString() = "${year}년"
    fun peopleToString() = if (peopleMin == peopleMax) "${peopleMin}인" else "$peopleMin ~ ${peopleMax}인"
    fun playTimeToString() =
        if (playTimeMin == playTimeMax) "${playTimeMin}분"
        else "$playTimeMin ~ ${playTimeMax}분"

    fun ageToString() = "${age}세 이상"
    fun gameLevelToString() : String {
        gameLevel = ReviewList.getAvgGameLevel(id)
        return "$gameLevel / 5"
    }
    fun rateToString() : String {
        rate = ReviewList.getAvgRate(id)
        return "$rate / 5"
    }
    fun genreToString(): String {
        var items: MutableList<String> = mutableListOf()
        for (item in genreList!!) {
            items.add(TagList.getGenreTitle(item))
        }
        return items.joinToString()
    }
    fun thumbToString() = thumbCnt.toString()
    fun commentToString() = ReviewList.getReviewList(id).size.toString()

    // 좋아요 버튼 눌렀을 때 실행
    fun setCheckGood() {
        isGood = !isGood

        if (isGood) thumbCnt++
        else thumbCnt--
    }
}