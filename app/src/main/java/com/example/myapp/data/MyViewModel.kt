package com.example.myapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MyViewModel(private val application:Application) : AndroidViewModel(application) {
    var dataList = mutableListOf<AllData>()
        private set

    init {
        //임시 데이터
        dataList.add(AllData("INDI STREET"))
        for(i: Int in 1..30)
            dataList.add(AllData())
    }
}