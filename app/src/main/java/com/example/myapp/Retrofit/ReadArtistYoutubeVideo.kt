package com.example.myapp.Retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun ReadArtistYoutubeVideo(artist: ArtistData, isShort:Boolean): List<String> {
    val listString = artist.youtubeVideoLink

    val id_lists = listString.split("/")

    val musicList = id_lists[0].split(" ")
    val shortList = id_lists[1].split(" ")

    if (isShort){
        return shortList.toList()
    }
    else{
        return musicList.toList()
    }
}

fun main() {

}