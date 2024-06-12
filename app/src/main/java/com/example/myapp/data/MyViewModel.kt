package com.example.myapp.data

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.Retrofit.ArtistData
import com.example.myapp.Retrofit.InitLiveList
import com.example.myapp.Retrofit.LiveData
import com.example.myapp.Retrofit.initArtistList
import kotlinx.coroutines.launch

class MyViewModel(private val application: Application) : AndroidViewModel(application) {
    var dataList = mutableListOf<ArtistData>()
        private set
    var dataList2 = mutableListOf<LiveData>()
        private set
    val isLoaded = mutableStateOf(false)

    init {
        Log.i("viewmodel", "init")
        InitData()
    }

    fun InitData() {
        viewModelScope.launch {
            initArtistList {
                dataList = it.toMutableList()
                isLoaded.value = true
            }
            InitLiveList {
                dataList2 = it.toMutableList()
            }
        }
    }
}