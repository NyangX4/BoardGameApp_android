package com.example.boardgame.model

// TODO : 인원수, 플레이시간 등 data 더 추가하기
class BoardGames {
    var id: Int? = 0
    var gameImage: Int? = 0
    var gameTitle: String? = null
    var gameLevel: Float? = 0.0f
    var thumbCnt: Int? = 0
    var commentCnt: Int? = 0

    constructor(id: Int?, gameImage: Int?, gameTitle: String?, gameLevel: Float?, thumbCnt: Int?, commentCnt: Int?) {
        this.id = id
        this.gameImage = gameImage
        this.gameTitle = gameTitle
        this.gameLevel = gameLevel
        this.thumbCnt = thumbCnt
        this.commentCnt = commentCnt
    }
}