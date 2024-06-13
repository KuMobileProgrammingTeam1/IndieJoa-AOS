package com.example.myapp.Retrofit

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getLiveList(
    page: Int = 0,
    size: Int = 20,
    name: String = "",
    sort: Int = 0,
    onSuccess: (List<LiveData>, Int) -> Unit
) {
    val retrofitService = RetrofitFactory.create()

    val call = retrofitService.getAllLives(page = page, size = size, name = name, sort = sort)

    call.enqueue(object : Callback<LiveResponse> {
        override fun onResponse(call: Call<LiveResponse>, response: Response<LiveResponse>) {
            if (response.isSuccessful) {
                val liveResponse = response.body()
                Log.i("GetLiveList", "testRetrofit OnResponse")
                Log.i("GetLiveList", "${liveResponse?.totalElements}")

                liveResponse?.content?.let {
                    onSuccess(it, liveResponse.totalPages)
                    Log.i("GetLiveList", "GetLiveList Success")
                }
            } else {
                Log.i("GetLiveList", "failed on response")
                getLiveList(onSuccess = onSuccess, page = page, size = size, name = name, sort = sort)
            }
        }

        override fun onFailure(p0: Call<LiveResponse>, p1: Throwable) {
            Log.e("GetLiveList", "onFailure: ${p1.message}")
            getLiveList(onSuccess = onSuccess, page = page, size = size, name = name, sort = sort)
        }
    })
}