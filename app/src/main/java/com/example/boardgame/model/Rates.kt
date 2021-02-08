package com.example.boardgame.model

import java.text.SimpleDateFormat
import java.util.*

class Rates : Comparable<Rates> {
    var id: Int = 0
    var gameId: Int = 0
    var name: String? = null
    var pwd: String? = null
    var date: Long = 0L // 평가를 작성한 날짜 (System.currentTimeMillis())
    var content: String? = null

    constructor(id: Int, gameId: Int, name: String, pwd: String, date: Long, content: String) {
        this.id = id
        this.gameId = gameId
        this.name = name
        this.pwd = pwd
        this.date = date
        this.content = content
    }

    // 최근 순으로 정렬
    override fun compareTo(other: Rates): Int =
        if (date < other.date) 1
        else if (date > other.date) -1
        else 0

    enum class TimeValue(val value: Int, val max: Int, val msg: String) {
        MIN(60, 60, "분 전"),
        HOUR(60, 24, "시간 전"),
        DAY(24, 30, "일 전"),
        MONTH(30, 12, "달 전")
    }

    // 한 달 이상 차이나면 작성된 날짜로 표시하기
    fun dateToString(): String {
        val curTime = System.currentTimeMillis()
        var diffTime = (curTime - date) / 1000
        var msg = ""

        if (diffTime < TimeValue.MIN.value) {
            // 몇 초 전일 경우
            msg = "방금 전"
        } else {
            for (i in TimeValue.values()) {
                diffTime /= i.value
                if (diffTime < i.max) {
                    return diffTime.toString() + i.msg
                }
            }
            msg = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(date)
        }
        return msg
    }
}