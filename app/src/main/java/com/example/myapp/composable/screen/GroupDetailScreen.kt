package com.example.myapp.composable.screen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.example.myapp.composable.component.ShowThumbnail
import com.example.myapp.data.MyViewModel
import com.example.myapp.MapActivity
import com.example.myapp.MapInfoActivity
import com.example.myapp.R

@Composable
fun GroupDetailScreen(myViewModel: MyViewModel, itemIndex: Int?) {
    val base_url = "https://indiejoa-api.fly.dev"

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        //정보 제공 화면
        item {
            Image(
                painter = painterResource(id = myViewModel.dataList[itemIndex!!].showImg),
                contentDescription = myViewModel.dataList[itemIndex!!].bandName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        //지도는 여기에 추가해주세요
        item{
            val context = LocalContext.current
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        val intent = Intent(context, MapActivity::class.java).apply{
                            putExtra("itemIndex", itemIndex)
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
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.kakaomap_basic),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("공연장 정보",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(8.dp))

                    }
                }
                Button(
                    onClick = {
                        val intent = Intent(context, MapInfoActivity::class.java).apply{
                            putExtra("itemIndex", itemIndex)
                        }
                        context.startActivity(intent)
                    },shape = RoundedCornerShape(12.dp),
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
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.kakaomap_basic),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("공연장 위치",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(8.dp))

                    }
                }
            }
        }
        item {
            Text(
                text = myViewModel.dataList[itemIndex!!].bandName,
                fontSize = 50.sp,
                modifier = Modifier.padding(bottom = 25.dp)
            )
        }
        item {
            Text(text = "${myViewModel.dataList[itemIndex!!].bandName}의 음악 리스트", fontSize = 25.sp)
        }
        //음악, 쇼츠 리스트화면
        item {
            ShowThumbnail(
                videoIds = myViewModel.dataList[itemIndex!!].musicList,
                Modifier
                    .size(320.dp, 180.dp)
                    .padding(8.dp)
            )
        }
        item {
            Text(text = "${myViewModel.dataList[itemIndex!!].bandName}의 쇼츠 리스트", fontSize = 25.sp)
        }
        item {
            ShowThumbnail(
                videoIds = myViewModel.dataList[itemIndex!!].shortList,
                Modifier
                    .size(160.dp, 160.dp)
                    .padding(8.dp)
            )
        }
    }


}