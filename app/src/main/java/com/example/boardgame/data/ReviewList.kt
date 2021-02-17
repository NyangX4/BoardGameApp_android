package com.example.boardgame.data

import android.util.Log
import com.example.boardgame.model.Review
import java.text.SimpleDateFormat
import java.util.*

object ReviewList {
    var nowId = 1
    private val dummyReview = mutableListOf(
        Review(
            nowId++, 1, "도검", "a1111", dateToMills("2016.09.08 18:10:45"),
            4.0f, 3.5f,
            "참 재밌는 게임이라는데... 이상하게 저는 드래프트가 들어간 게임들에서 재미를 못느끼네요. 7원더스, 노틀담 등\n" +
                    " \n" +
                    "게임 진행도 너무 더디게 느껴지고..\n" +
                    " \n" +
                    "뭐 하고 싶은게 있어도 절차가 너무 까다롭고... 카드를 몰라서 그런지 뭘 해야될지 감이 안오더군요.\n" +
                    " \n" +
                    "다시 해보면 어떨지...\n"
        ),
        Review(
            nowId++, 1, "Hanu", "222b2", dateToMills("2021.01.23 12:11:55"),
            5.0f, 2.5f,
            "보라와 긱 모두에서 최상위권에 랭크된 게임이라 덜컥 주문부터 했다가 컴포불량으로 환불하게 되어 게임의 깊이를 반에 반도 느끼지 못한 게임입니다ㅜㅜ 알록달록한 수많은 카드와 그안의 일러스트, 텍스트 읽는 소소한 재미가 좋았던 기억입니다. 다인플은 못해봤으나 1인플시 경쟁 없이 시간 내 화성을 테라포밍한다는 목표에만 몰입해도 되서 좋았습니다. 다만 솔플은 기업상이나 다른 경쟁요소가 없어 여러 기업으로 한번 테라포밍 성공하면 굳이 또 해보고 싶을거 같진 않을거 같단 생각입니다.."
        ),
        Review(
            nowId++, 2, "코코넛쉬림프", "3333", dateToMills("2021.02.07 20:36:20"),
            3.5f, 3.5f,
            "글룸 하다보면 재미있는 순간이 많은데 스포때문에 공유를 많이 못하는게 아쉽네요 ㅎㅎ\n" +
                    "\n" +
                    "뱀때한테 둘러쌓인 의무관 할배"
        ),
        Review(
            nowId++, 1, "이탬", "930718", System.currentTimeMillis(),
            4.5f, 3.0f,
            "보라와 긱 모두에서 최상위권에 랭크된 게임이라 덜컥 주문부터 했다가 컴포불량으로 환불하게 되어 게임의 깊이를 반에 반도 느끼지 못한 게임입니다ㅜㅜ 알록달록한 수많은 카드와 그안의 일러스트, 텍스트 읽는 소소한 재미가 좋았던 기억입니다. 다인플은 못해봤으나 1인플시 경쟁 없이 시간 내 화성을 테라포밍한다는 목표에만 몰입해도 되서 좋았습니다. 다만 솔플은 기업상이나 다른 경쟁요소가 없어 여러 기업으로 한번 테라포밍 성공하면 굳이 또 해보고 싶을거 같진 않을거 같단 생각입니다.."
        )
    )

    fun getReviewList(gameId : Int) = dummyReview.sorted().filter { it.gameId == gameId }
    fun getReview(reviewId : Int) : Review? = dummyReview.find { it.id == reviewId }
    fun addReview(new : Review) = dummyReview.add(new)
    fun removeReview(reviewId: Int) = dummyReview.remove(dummyReview.find { it.id == reviewId }) // TODO : code check

    private fun dateToMills(date: String): Long {
        val date = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault()).parse(date)
        Log.i("current Time", SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault()).format(System.currentTimeMillis()))
        return date!!.time
    }
}