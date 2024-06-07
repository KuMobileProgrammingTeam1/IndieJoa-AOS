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
                    if (artist.name == "이런") {
                        println("artistName : ${artist.name}")
                        println("artistID : ${artist.artistId}")
                        println("youtubeVideoLink : ${artist.youtubeVideoLink}")
                        TestRetrofitInput(artist)
                        println("artistName : ${artist.name}")
                        println("artistID : ${artist.artistId}")
                        println("youtubeVideoLink : ${artist.youtubeVideoLink}")
                    }
                }
            } else {
                println("failed on response")
            }
        }

        override fun onFailure(p0: Call<ArtistResponse>, p1: Throwable) {
            println("failed on onfailure")
        }
    })
}

fun TestRetrofitInput(artist: ArtistData) {
    val retrofitService = RetrofitFactory.create()

    val tmp = artist.copy(youtubeVideoLink = "test")

    val call = retrofitService.updateArtist(tmp)

    call.enqueue(object : Callback<Unit> {
        override fun onResponse(p0: Call<Unit>, p1: Response<Unit>) {
            println("Success to Update ${artist.name}")
        }

        override fun onFailure(p0: Call<Unit>, p1: Throwable) {
            println("Fail to Update ${artist.name}")
        }

    })
}

fun main() {
    TestRetrofit()
}