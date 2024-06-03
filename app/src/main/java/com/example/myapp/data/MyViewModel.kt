package com.example.myapp.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel

class MyViewModel(private val application: Application) : AndroidViewModel(application) {
    var dataList = mutableListOf<AllData>()
        private set

    init {
        //밴드 디테일 화면 실험용
        val bandName = "제시 바베라"
        val musicList =
            listOf("nh29uTWeK3A", "p5MSR2-SLd4", "fACfypWLqXg", "JY3BEc2j1NI", "qExLcd2ZYjA")
        val shortList =
            listOf("5DDBkBcN43E", "4-5A2S09FGg", "kvaCtrdGn-w", "DvIX9IAVF1w", "g6wZAa7FAI8")

        val tmp = AllData(bandName = bandName, musicList = musicList, shortList = shortList)
        dataList.add(tmp)

        //임시 데이터
        dataList.add(AllData("INDI STREET"))
        for (i: Int in 1..30) dataList.add(AllData())
    }
}