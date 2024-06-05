package com.example.myapp.Retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {

    @GET("artist")
    fun getAllArtists(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10
    ): Call<ArtistResponse>

    @POST("artist")
    fun updateArtist(
        @Body artistData: ArtistData
    ): Call<Unit>
}