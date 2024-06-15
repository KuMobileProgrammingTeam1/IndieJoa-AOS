package com.example.myapp.composable.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapp.R
import com.example.myapp.Retrofit.ArtistData
import com.example.myapp.data.MyViewModel

@Composable
fun HomeItem(artistData: ArtistData, myViewModel: MyViewModel, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = artistData.imageUrl,
            contentDescription = artistData.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(175.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    myViewModel.selectedArtistData = artistData
                    navController.navigate(route = "GroupDetailScreen")
                },
            error = painterResource(id = R.drawable.defaultimg)
        )

        Column {
            Text(
                color = Color.White,
                text = artistData.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}