package com.example.myapp.Retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {

    @GET("/artists")
    fun getAllArtists(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10
    ): Call<ArtistResponse>

    @POST("/artist")
    fun updateArtist(
        @Body artistData: ArtistData
    ): Call<Unit>

    @GET("/lives")
    fun getAllLives(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10
    ): Call<LiveResponse>

    @POST("/lives")
    fun updateLive(
        @Body LiveData: LiveData
    ): Call<Unit>
}