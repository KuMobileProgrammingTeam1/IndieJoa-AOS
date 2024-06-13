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
fun GroupDetailScreen2(myViewModel: MyViewModel) {
    if (myViewModel.selectedLiveData == null) return

    val liveData = myViewModel.selectedLiveData!!

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        item {
            AsyncImage(
                model = liveData.posterUrl,
                contentDescription = liveData.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(),
                error = painterResource(id = R.drawable.defaultimg)
            )
        }
        item {
            Text(
                text = liveData.description,
                fontSize = 23.sp,
                modifier = Modifier.padding(bottom = 25.dp)
            )
        }
    }
}
