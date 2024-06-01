package com.example.myapp.data

import com.example.myapp.R
import java.util.Date

data class AllData(
    val bandName:String = "no band name",
    val bandImg:Int= R.drawable.defaultimg,
    val bandIntro:String = "no band intro",

    val musicList:List<String> = listOf(),
    val shortList:List<String> = listOf(),

    val showName:String = "no show name",
    val showImg:Int = R.drawable.defaultimg,
    val showIntro:String = "no show intro",
    val showDate:Date = Date(1000,10,10),
    val showLocation:String = "no location"
    )