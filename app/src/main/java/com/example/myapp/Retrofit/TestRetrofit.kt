package com.example.myapp.Retrofit

import android.util.Log
import retrofit2.*

fun TestRetrofit() {
    val retrofitService = RetrofitFactory.create()

    val call = retrofitService.getAllArtists(page = 0, size = 5000)

    call.enqueue(object : Callback<ArtistResponse> {
        override fun onResponse(call: Call<ArtistResponse>, response: Response<ArtistResponse>) {
            if (response.isSuccessful) {
                val artistResponse = response.body()
                println("testRetrofit OnResponse")
                println("${artistResponse?.totalElements}")

                artistResponse?.content?.forEach { artist ->
                    println(artist.name)
                    println(artist.id)
                    println(artist.imageUrl)

                    if(artist.name == "제시 바레라"){
//                        print(ReadArtistYoutubeVideo(artist, false))
//                        UpdateArtistYoutubeVideo(artist)
//                        println("제시 바레라 정보")
//                        println(artist.youtubeVideoLink)
                    }


                }
            } else {
                println("failed on response")
                TestRetrofit()
            }
        }

        override fun onFailure(p0: Call<ArtistResponse>, p1: Throwable) {
            println("failed on onfailure")
            TestRetrofit()
        }
    })
}

fun main() {
    TestRetrofit()
}