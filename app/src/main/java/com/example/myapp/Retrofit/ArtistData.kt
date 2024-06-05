package com.example.myapp.Retrofit

data class ArtistData(
    val artistId: Int,
    val name: String,
    val nameEn: String,
    val nameJp: String,
    val isSolo: Boolean,
    val imageUrl: String,
    val youtubeChannelLink: String,
    val twitterLink: String,
    val youtubeVideoLink: String
)
