package com.example.myapp.stage

import android.os.Bundle
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

class MapActivity : ComponentActivity() {
    private val addressViewModel: AddressViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapScreen()
        }

        // 특정 id로 주소 가져오기
        val addressId: Long = 1
        addressViewModel.fetchAddressById(addressId)
    }

    @Composable
    fun MapScreen() {
        val address by addressViewModel.address.observeAsState()
        val errorMessage by addressViewModel.errorMessage.observeAsState()

        Column(modifier = Modifier.padding(16.dp)) {
            errorMessage?.let {
                Text(text = it)
            }

            address?.let {
                val placeLink = it.placeLink
                AndroidView(factory = {
                    WebView(it).apply {
                        webViewClient = WebViewClient()
                        settings.javaScriptEnabled = true
                        loadUrl(placeLink)
                    }
                }, modifier = Modifier.fillMaxSize())
            }
        }
    }
}