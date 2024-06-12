package com.example.myapp.data

import android.app.Application
import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapp.Retrofit.ArtistData
import com.example.myapp.Retrofit.InitArtistList
import com.example.myapp.Retrofit.InitLiveList
import com.example.myapp.Retrofit.LiveData
import kotlinx.coroutines.launch
import java.util.stream.Collectors

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
    fun InitData(){
        viewModelScope.launch {
            InitArtistList {
                dataList = it.toMutableList()
                isLoaded.value = true
            }
            InitLiveList {
                dataList2 = it.toMutableList()
                isLoaded.value = true
            }
        }
    }
}