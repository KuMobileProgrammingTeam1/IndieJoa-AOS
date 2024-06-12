package com.example.myapp.Retrofit

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun GetArtistList(page: Int = 0, size: Int = 100, onSuccess: (List<ArtistData>, Int) -> Unit) {
    val retrofitService = RetrofitFactory.create()

    val call = retrofitService.getAllArtists(page = page, size = size)

    call.enqueue(object : Callback<ArtistResponse> {
        override fun onResponse(call: Call<ArtistResponse>, response: Response<ArtistResponse>) {
            if (response.isSuccessful) {
                val artistResponse = response.body()
                Log.i("GetArtistList", "testRetrofit OnResponse")
                Log.i("GetArtistList", "${artistResponse?.totalElements}")

                artistResponse?.content?.let {
                    onSuccess(it, artistResponse.totalElements)
                    Log.i("GetArtistList", "GetArtistList Success")
                }
            } else {
                Log.i("GetArtistList", "failed on response")
                GetArtistList(onSuccess = onSuccess, page = page, size = size)
            }
        }

        override fun onFailure(p0: Call<ArtistResponse>, p1: Throwable) {
            Log.e("GetArtistList", "onFailure: ${p1.message}")
            GetArtistList(onSuccess = onSuccess, page = page, size = size)
        }
    })
}