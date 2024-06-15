package com.example.myapp.composable.screen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapp.MapActivity
import com.example.myapp.MapChannelActivity
import com.example.myapp.R
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
            val context = LocalContext.current
            val stageId = liveData.stageId
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(3.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        val intent = Intent(context, MapActivity::class.java).apply {
                            putExtra("stageId", stageId)
                        }
                        context.startActivity(intent)
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFEB3B),
                        contentColor = Color.Black
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 12.dp
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.kakaomap_basic),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "공연장 위치",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(8.dp)
                        )

                    }
                }
                Button(
                    onClick = {
                        val intent = Intent(context, MapChannelActivity::class.java).apply {
                            putExtra("stageId", stageId)
                        }
                        context.startActivity(intent)
                    }, shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 12.dp
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.youtube),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "공연장 유튜브",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(8.dp)
                        )

                    }
                }
            }
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
