package com.example.myapp.Retrofit

import android.util.Log
import retrofit2.*

fun TestRetrofit(){
    val retrofitService = RetrofitFactory.create()

    val call = retrofitService.getAllArtists(page = 0, size = 10)

    call.enqueue(object : Callback<ArtistResponse>{
        override fun onResponse(call: Call<ArtistResponse>, response: Response<ArtistResponse>) {
            if(response.isSuccessful){
                val artistResponse = response.body()
                println("testRetrofit OnResponse")
                println("${artistResponse?.totalElements}")

                artistResponse?.content?.forEach{
                    artist->
                    println("artistName : ${artist.name}")
                }
            }
            else{
                println("failed on response")
            }
        }

        override fun onFailure(p0: Call<ArtistResponse>, p1: Throwable) {
            println("failed on onfailure")
        }
    })
}

fun main(){
    TestRetrofit()
}