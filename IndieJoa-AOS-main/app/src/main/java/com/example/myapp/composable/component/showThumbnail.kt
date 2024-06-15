package com.example.myapp.composable.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ShowThumbnail(videoIds: List<String>, modifier: Modifier) {
    val context = LocalContext.current
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(8.dp)
    ) {
        item { 
            if(videoIds.isEmpty()){
                Text(text = "목록이 비어있습니다.", fontSize = 35.sp)
            }
        }
        items(videoIds) { videoId ->
            val imageUrl = "https://img.youtube.com/vi/$videoId/0.jpg"
            val videoUrl = "https://www.youtube.com/watch?v=$videoId"
            val shortsUrl = "https://www.youtube.com/shorts/$videoId"
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = modifier
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(shortsUrl))
                        context.startActivity(intent)
                    },
                contentScale = ContentScale.Crop
            )
        }
    }
}