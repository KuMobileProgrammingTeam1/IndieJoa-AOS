package com.example.myapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import java.util.Date

class MyViewModel(private val application:Application) : AndroidViewModel(application) {
    var dataList = mutableListOf<AllData>()
        private set

    init {
        dataList.add(AllData(bandName = "2022년 55000원", showDate = Date(2022, 10, 10), showPrice = 55000))
        dataList.add(AllData(bandName = "2023년 45000원", showDate = Date(2023, 10, 10), showPrice = 45000))
        dataList.add(AllData(bandName = "2024년 35000원", showDate = Date(2024, 10, 10), showPrice = 35000))
        dataList.add(AllData(bandName = "2025년 25000원", showDate = Date(2025, 10, 10), showPrice = 25000))
    }
}