package com.example.myapp.data

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.Retrofit.ArtistData
import com.example.myapp.Retrofit.GetArtistList
import kotlinx.coroutines.launch

class MyViewModel(private val application: Application) : AndroidViewModel(application) {
    var dataList = mutableListOf<ArtistData>()
        private set
    val isLoaded = mutableStateOf(false)
    var artistCount = 0

    fun UpdateDataList(page: Int = 0, size: Int = 100) {
        isLoaded.value = false
        viewModelScope.launch {
            GetArtistList(page = page, size = size) { artistList, artistNumber ->
                dataList = artistList.toMutableList()
                artistCount = artistNumber
                isLoaded.value = true
            }
        }
    }
}