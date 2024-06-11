package com.example.myapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myapp.stage.AddressViewModel

class MapActivity : ComponentActivity() {
    private val addressViewModel: AddressViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MapScreen()
        }
        val page: Int = 0
        val size: Int = 100
        addressViewModel.fetchAddresses(page, size)
    }

    @Composable
    fun MapScreen() {
        val responseData by addressViewModel.responseData.observeAsState()
        val errorMessage by addressViewModel.errorMessage.observeAsState()



        Column(modifier = Modifier.padding(16.dp)) {
            errorMessage?.let {
                Text(text = it)
            }

            responseData?.let { data ->
                val targetId = intent.getIntExtra("itemIndex", -1).toLong()
                Log.d("AddressViewModel", "targetId: $targetId")
                val mapId = targetId + 1
                val content = data.content.find { it.id == mapId}
                    content?.let { addr ->
                    val placeLink = addr.placeLink
                    Log.d("AddressViewModel", "addr: $addr")
                    Log.d("AddressViewModel", "placeLink: $placeLink")
                    Text(text = "주소: ${addr.address}")
                    Text(text = "PlaceLink: $placeLink")
                    AndroidView(factory = {
                        WebView(it).apply {
                            webViewClient = WebViewClient()
                            settings.javaScriptEnabled = true
                            loadUrl(placeLink)
                        }
                    }, modifier = Modifier.fillMaxSize())
                } ?: run {
                    Text(text = "No address found", modifier = Modifier.fillMaxSize())
                }
            } ?: run {
                Text(text = "Loading...", modifier = Modifier.fillMaxSize())
            }
        }
    }
}