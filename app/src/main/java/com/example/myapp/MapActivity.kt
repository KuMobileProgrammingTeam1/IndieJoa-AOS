package com.example.myapp

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myapp.stage.AddressViewModel

class MapActivity : ComponentActivity() {
    private val addressViewModel: AddressViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val targetId = intent.getIntExtra("stageId", -1)
        if (targetId != -1) {
            addressViewModel.getAddresses(targetId)
        } else {
            Log.e("MapActivity", "Invalid stageId received")
        }

        setContent {
            MapScreen()
        }

    }

    @Composable
    fun MapScreen() {
        val stageData by addressViewModel.responseData.observeAsState()
        val errorMessage by addressViewModel.errorMessage.observeAsState()
        val isLoaded = addressViewModel.isStageDataLoaded.value

        Column(modifier = Modifier.padding(16.dp)) {
            errorMessage?.let {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = it, fontSize = 25.sp)
                }
            }

            if (!isLoaded) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Now Loading...", fontSize = 25.sp, color = Color.White)
                }
                return
            }

            stageData?.let { data ->
                val placeLink = data.placeLink
                if (placeLink.isBlank()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "address NOT FOUND", fontSize = 25.sp)
                    }
                    return
                }
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
