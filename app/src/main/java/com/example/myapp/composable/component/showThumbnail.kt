package com.example.myapp.composable.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapp.R

@Composable
fun ShowThumbnail(videoIds: List<String>, modifier: Modifier) {
    val context = LocalContext.current
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(8.dp)
    ) {
        item {
            if (videoIds.isEmpty()) {
                Image(
                    painter = painterResource(id = R.drawable.black_notification),
                    contentDescription = "Blank"
                )
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