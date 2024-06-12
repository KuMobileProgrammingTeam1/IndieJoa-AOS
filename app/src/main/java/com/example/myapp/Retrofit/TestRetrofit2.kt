package com.example.myapp.Retrofit

import android.util.Log
import retrofit2.*

fun TestRetrofit2() {
    val retrofitService = RetrofitFactory.create()

    val call = retrofitService.getAllLives(page = 0, size = 6500)

    call.enqueue(object : Callback<LiveResponse> {
        override fun onResponse(call: Call<LiveResponse>, response: Response<LiveResponse>) {
            if (response.isSuccessful) {
                val liveResponse = response.body()
                liveResponse?.content?.forEach { live ->
                    println("live =============")
                    println(live.id + live.indieStreetId)
                    println(live.indieStreetId)
                    println(live.title)
                    println("live =============")
                }
            } else {
                println("failed on response")
                TestRetrofit()
            }
        }

        override fun onFailure(p0: Call<LiveResponse>, p1: Throwable) {
            println("failed on onfailure")
            TestRetrofit()
        }
    })
}

fun main() {
    TestRetrofit()
    TestRetrofit2()
}