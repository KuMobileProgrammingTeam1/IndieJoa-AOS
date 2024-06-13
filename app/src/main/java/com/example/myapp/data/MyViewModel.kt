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
    val isArtistListLoaded = mutableStateOf(false)
    var pageNum = 0

//    init {
//        Log.i("viewmodel", "init")
//        InitData()
//    }
//
//    fun InitData() {
//        viewModelScope.launch {
//            initArtistList {
//                dataList = it.toMutableList()
//                isLoaded.value = true
//            }
//            InitLiveList {
//                dataList2 = it.toMutableList()
//            }
//        }
//    }

    fun updateArtistList(page: Int = 0, size: Int = 20, name: String = "") {
        isArtistListLoaded.value = false
        viewModelScope.launch {
            getArtistList(page = page, size = size, name = name) { resList, res_num ->
                artistList = resList.toMutableList()
                pageNum = res_num
                isArtistListLoaded.value = true
            }
        }
    }
}