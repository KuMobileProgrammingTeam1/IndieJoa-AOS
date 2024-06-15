package com.example.myapp.stage

import com.example.myapp.Retrofit.StageData
import retrofit2.http.GET
import retrofit2.http.Query

interface StageAddressService {
    @GET("/stage")
    suspend fun getAddressById(
        @Query("indieStreetId") id: Int
    ): StageData
}