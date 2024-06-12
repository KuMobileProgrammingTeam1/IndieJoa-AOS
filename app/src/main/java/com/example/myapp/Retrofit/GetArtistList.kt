package com.example.myapp.Retrofit

import android.util.Log
import retrofit2.*

fun GetArtistList(page:Int = 0, size:Int = 100, onSuccess: (List<ArtistData>) -> Unit) {
    val retrofitService = RetrofitFactory.create()

    val call = retrofitService.getAllArtists(page = page, size = size)

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
                GetArtistList(onSuccess = onSuccess, page = page, size = size)
            }
        }

        override fun onFailure(p0: Call<ArtistResponse>, p1: Throwable) {
            Log.e("InitArtistList", "onFailure: ${p1.message}")
            GetArtistList(onSuccess = onSuccess, page = page, size = size)
        }
    })
}