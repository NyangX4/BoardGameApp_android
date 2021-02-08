package com.example.boardgame.data

import android.util.Log
import com.example.boardgame.model.Rates
import java.text.SimpleDateFormat
import java.util.*

object RateList {
    private val dummyRate = listOf(
        Rates(
            1, 1, "도검", "a1111", dateToMills("2016.09.08 18:10:45"),
            "그냥 심심해서 기억나는 게임들에 대한 간단히 적어봅니다.\n" +
                    "호평할건 하고 악평할건 하고 논란이 있더라도 가감없이 적어보구요.\n" +
                    "순서는 생각나는대로 막 적습니다. 게이머 게임부터 유아용까지 있습니다."
        ),
        Rates(
            2, 1, "Hanu", "222b2", dateToMills("2021.01.23 12:11:55"),
            "보라와 긱 모두에서 최상위권에 랭크된 게임이라 덜컥 주문부터 했다가 컴포불량으로 환불하게 되어 게임의 깊이를 반에 반도 느끼지 못한 게임입니다ㅜㅜ 알록달록한 수많은 카드와 그안의 일러스트, 텍스트 읽는 소소한 재미가 좋았던 기억입니다. 다인플은 못해봤으나 1인플시 경쟁 없이 시간 내 화성을 테라포밍한다는 목표에만 몰입해도 되서 좋았습니다. 다만 솔플은 기업상이나 다른 경쟁요소가 없어 여러 기업으로 한번 테라포밍 성공하면 굳이 또 해보고 싶을거 같진 않을거 같단 생각입니다.."
        ),
        Rates(
            3, 2, "코코넛쉬림프", "3333", dateToMills("2021.02.07 20:36:20"),
            "글룸 하다보면 재미있는 순간이 많은데 스포때문에 공유를 많이 못하는게 아쉽네요 ㅎㅎ\n" +
                    "\n" +
                    "뱀때한테 둘러쌓인 의무관 할배"
        ),
        Rates(
            4, 1, "이탬", "4444", System.currentTimeMillis(),
            "보라와 긱 모두에서 최상위권에 랭크된 게임이라 덜컥 주문부터 했다가 컴포불량으로 환불하게 되어 게임의 깊이를 반에 반도 느끼지 못한 게임입니다ㅜㅜ 알록달록한 수많은 카드와 그안의 일러스트, 텍스트 읽는 소소한 재미가 좋았던 기억입니다. 다인플은 못해봤으나 1인플시 경쟁 없이 시간 내 화성을 테라포밍한다는 목표에만 몰입해도 되서 좋았습니다. 다만 솔플은 기업상이나 다른 경쟁요소가 없어 여러 기업으로 한번 테라포밍 성공하면 굳이 또 해보고 싶을거 같진 않을거 같단 생각입니다.."
        )
    )

    fun getRateList(gameId : Int) = dummyRate.sorted().filter { it.gameId == gameId }
    private fun dateToMills(date: String): Long {
        val date = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault()).parse(date)
        Log.i("current Time", SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault()).format(System.currentTimeMillis()))
        return date!!.time
    }
}