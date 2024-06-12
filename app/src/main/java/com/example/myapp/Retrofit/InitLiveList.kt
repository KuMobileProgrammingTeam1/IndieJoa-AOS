package com.example.myapp.Retrofit

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun InitLiveList(onSuccess: (List<LiveData>) -> Unit) {
    val retrofitService = RetrofitFactory.create()

    val call: Call<LiveResponse> = retrofitService.getAllLives(page = 0, size = 20)

    call.enqueue(object : Callback<LiveResponse> {
        override fun onResponse(call: Call<LiveResponse>, response: Response<LiveResponse>) {
            if (response.isSuccessful) {
                val liveResponse = response.body()
                Log.i("InitLiveList", "testRetrofit OnResponse")
                Log.i("InitLiveList", "${liveResponse?.totalElements}")


                liveResponse?.content?.let {
                    onSuccess(it)
                    Log.i("InitLiveList", "InitLiveList Success")
                }
            } else {
                Log.i("InitLiveList", "failed on response")
//                InitLiveList(onSuccess = onSuccess)
            }
        }

        override fun onFailure(p0: Call<LiveResponse>, p1: Throwable) {
            Log.e("InitArtistList", "onFailure: ${p1.message}")
            InitLiveList(onSuccess = onSuccess)
        }
    })
}