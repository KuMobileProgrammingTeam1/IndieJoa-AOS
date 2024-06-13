package com.example.myapp.Retrofit

data class LiveData(
    val id: Int,
    val indieStreetId: Int,
    val stageId: Int,
    val title: String,
    val description: String,
    val posterUrl: String,
    val purchaseTicketLink: String,
    var priceInfo: String,
    var startDate: String,
    var endDate: String
)
