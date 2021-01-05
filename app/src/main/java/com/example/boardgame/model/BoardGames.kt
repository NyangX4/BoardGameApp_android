package com.example.boardgame.model

class BoardGames {
    var gameImage : Int? = 0
    var gameTitle : String? = null
    var gameLevel : Float? = 0.0f
    var thumbCnt : Int? = 0
    var commentCnt : Int? = 0


    constructor(gameImage : Int?, gameTitle : String?, gameLevel : Float?, thumbCnt : Int?, commentCnt : Int?) {
        this.gameImage = gameImage
        this.gameTitle = gameTitle
        this.gameLevel = gameLevel
        this.thumbCnt = thumbCnt
        this.commentCnt = commentCnt
    }
}