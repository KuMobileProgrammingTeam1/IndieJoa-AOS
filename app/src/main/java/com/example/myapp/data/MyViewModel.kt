package com.example.myapp.data

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.Retrofit.ArtistData
import com.example.myapp.Retrofit.LiveData
import com.example.myapp.Retrofit.getArtistList
import kotlinx.coroutines.launch

class MyViewModel(private val application: Application) : AndroidViewModel(application) {
    var dataList = mutableListOf<ArtistData>()
        private set
    var dataList2 = mutableListOf<LiveData>()
        private set
    val isLoaded = mutableStateOf(false)

    var artistList = mutableListOf<ArtistData>()
    var selectedArtistData: ArtistData? = null
    val isArtistListLoaded = mutableStateOf(false)
    var artistLastPage = 1

    fun refreshArtistList(page: Int = 0, size: Int = 20, name: String = "") {
        isArtistListLoaded.value = false
        viewModelScope.launch {
            getArtistList(page = page, size = size, name = name) { resList, resNum ->
                artistList = resList.toMutableList()
                artistLastPage = resNum
                isArtistListLoaded.value = true
            }
        }
    }
}