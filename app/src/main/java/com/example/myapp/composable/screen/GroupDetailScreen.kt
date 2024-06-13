package com.example.myapp.composable.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapp.R
import com.example.myapp.Retrofit.ReadArtistYoutubeVideo
import com.example.myapp.composable.component.ShowThumbnail
import com.example.myapp.data.MyViewModel

@Composable
fun GroupDetailScreen(myViewModel: MyViewModel) {
    if (myViewModel.selectedArtistData == null) return

    val artistData = myViewModel.selectedArtistData!!

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        item {
            AsyncImage(
                model = artistData.imageUrl,
                contentDescription = artistData.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(),
                error = painterResource(id = R.drawable.defaultimg)
            )
        }
        item {
            Text(
                text = artistData.name,
                fontSize = 50.sp,
                modifier = Modifier.padding(bottom = 25.dp)
            )
        }
        item {
            Text(text = "${artistData.name}의 음악 리스트", fontSize = 25.sp)
        }
        item {
            ShowThumbnail(
                videoIds = ReadArtistYoutubeVideo(artistData, false),
                Modifier
                    .size(320.dp, 180.dp)
                    .padding(8.dp)
            )
        }
        item {
            Text(text = "${artistData.name}의 쇼츠 리스트", fontSize = 25.sp)
        }
        item {
            ShowThumbnail(
                videoIds = ReadArtistYoutubeVideo(artistData, true),
                Modifier
                    .size(160.dp, 160.dp)
                    .padding(8.dp)
            )
        }
    }
}
