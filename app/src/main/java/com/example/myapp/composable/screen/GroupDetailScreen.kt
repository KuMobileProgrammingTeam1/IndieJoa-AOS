package com.example.myapp.composable.screen

import androidx.compose.foundation.Image
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
fun GroupDetailScreen(myViewModel: MyViewModel, itemIndex: Int?) {
    if (itemIndex == null) {
        Text(text = "ERROR_itemIndex_is_null", fontSize = 100.sp)
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        //정보 제공 화면
        item {
            if (myViewModel.artistList[itemIndex].imageUrl.isNotBlank()) {
                AsyncImage(
                    model = myViewModel.artistList[itemIndex].imageUrl,
                    contentDescription = myViewModel.artistList[itemIndex].name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.defaultimg),
                    contentDescription = myViewModel.artistList[itemIndex].name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        //지도는 여기에 추가해주세요
        item {
            Text(
                text = myViewModel.artistList[itemIndex].name,
                fontSize = 50.sp,
                modifier = Modifier.padding(bottom = 25.dp)
            )
        }
        item {
            Text(text = "${myViewModel.artistList[itemIndex].name}의 음악 리스트", fontSize = 25.sp)
        }
        //음악, 쇼츠 리스트화면
        item {
            ShowThumbnail(
                videoIds = ReadArtistYoutubeVideo(myViewModel.artistList[itemIndex], false),
                Modifier
                    .size(320.dp, 180.dp)
                    .padding(8.dp)
            )
        }
        item {
            Text(text = "${myViewModel.artistList[itemIndex].name}의 쇼츠 리스트", fontSize = 25.sp)
        }
        item {
            ShowThumbnail(
                videoIds = ReadArtistYoutubeVideo(myViewModel.artistList[itemIndex], true),
                Modifier
                    .size(160.dp, 160.dp)
                    .padding(8.dp)
            )
        }
    }
}