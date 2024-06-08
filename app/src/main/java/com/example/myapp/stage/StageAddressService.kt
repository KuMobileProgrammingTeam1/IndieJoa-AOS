package com.example.myapp.stage

import retrofit2.http.GET
import retrofit2.http.Query

interface StageAddressService {
    @GET("/stages")
    suspend fun getAddressById(@Query("id") id: Long): StageAddress
}