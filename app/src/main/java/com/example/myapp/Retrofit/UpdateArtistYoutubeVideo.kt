package com.example.myapp.Retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun UpdateArtistYoutubeVideo(artist: ArtistData) {

    val retrofitService = RetrofitFactory.create()

    val bandName = "제시 바레라"

    if(artist.name!=bandName)return

    val musicList =
        listOf("nh29uTWeK3A", "p5MSR2-SLd4", "fACfypWLqXg", "JY3BEc2j1NI", "qExLcd2ZYjA")
    val shortList =
        listOf("5DDBkBcN43E", "4-5A2S09FGg", "kvaCtrdGn-w", "DvIX9IAVF1w", "g6wZAa7FAI8")

    var listString = ""

    musicList.forEachIndexed { idx, it ->
        if (idx != 0) {
            listString += " "
        }
        listString += it
    }

    listString += "/"

    shortList.forEachIndexed { idx, it ->
        if (idx != 0) {
            listString += " "
        }
        listString += it
    }

    println(listString)

    val tmp = artist.copy(youtubeVideoLink = listString)

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
//    UpdateArtistYoutubeVideo()
}