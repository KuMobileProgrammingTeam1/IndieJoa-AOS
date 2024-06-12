package com.example.myapp.Retrofit

import android.util.Log
import retrofit2.*

fun InitArtistList(onSuccess: (List<ArtistData>) -> Unit) {
    val retrofitService = RetrofitFactory.create()

    val call = retrofitService.getAllArtists(page = 0, size = 5000)

    call.enqueue(object : Callback<ArtistResponse> {
        override fun onResponse(call: Call<ArtistResponse>, response: Response<ArtistResponse>) {
            if (response.isSuccessful) {
                val artistResponse = response.body()
                Log.i("InitArtistList","testRetrofit OnResponse")
                Log.i("InitArtistList","${artistResponse?.totalElements}")

                artistResponse?.content?.let{
                    onSuccess(it)
                    Log.i("InitArtistList", "InitArtistList Success")
                }
            } else {
                Log.i("InitArtistList","failed on response")
//                InitArtistList(onSuccess = onSuccess)
            }
        }

        override fun onFailure(p0: Call<ArtistResponse>, p1: Throwable) {
            Log.e("InitArtistList", "onFailure: ${p1.message}")
            InitArtistList(onSuccess = onSuccess)
        }
    })
}