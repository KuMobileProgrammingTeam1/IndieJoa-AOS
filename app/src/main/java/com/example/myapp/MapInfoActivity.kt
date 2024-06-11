package com.example.myapp

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myapp.stage.AddressViewModel

class MapInfoActivity: ComponentActivity() {
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

    fun convertKakaoMapUrl(originUrl: String): String {
        val regex = "https://place.map.kakao.com/(\\d+)".toRegex()
        val matchResult = regex.find(originUrl)
        return if (matchResult != null) {
            val id = matchResult.groupValues[1]
            Log.d("AddressViewModel", "id: $id")
            "https://map.kakao.com/link/map/$id"
        } else {
            originUrl
        }
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

                val content = data.content.find { it.id == targetId}
                content?.let { addr ->
                    val placeLink = convertKakaoMapUrl(addr.placeLink)
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