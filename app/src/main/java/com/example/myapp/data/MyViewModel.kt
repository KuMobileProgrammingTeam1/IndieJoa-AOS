package com.example.myapp.data

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapp.Retrofit.ArtistData
import com.example.myapp.Retrofit.InitArtistList
import kotlinx.coroutines.launch

class MyViewModel(private val application: Application) : AndroidViewModel(application) {
    var dataList = mutableListOf<ArtistData>()
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
                Log.i("viewmodel", "finished")
                Log.i("viewmodel", "${dataList.count()}")
            }
        }
    }
}