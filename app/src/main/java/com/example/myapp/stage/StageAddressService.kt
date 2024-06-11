package com.example.myapp.stage

import com.example.myapp.Retrofit.StageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StageAddressService {
    @GET("/stages")
    suspend fun getAddressById(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10
    ): StageResponse
}