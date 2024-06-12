package com.example.myapp.composable.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.myapp.data.MyViewModel

@Composable
fun LiveItem(myViewModel: MyViewModel, itemIndex:Int, navController: NavController) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally){

        if(myViewModel.dataList2[itemIndex].posterUrl.isNotBlank()){
            AsyncImage(
                model = myViewModel.dataList2[itemIndex].posterUrl,
                contentDescription = myViewModel.dataList2[itemIndex].title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(175.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        navController.navigate(route = "GroupDetailScreen2/$itemIndex")
                    }
            )
        }
        else{
            Image(
                painterResource(id = R.drawable.defaultimg),
                contentDescription = myViewModel.dataList[itemIndex].name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(175.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        navController.navigate(route = "GroupDetailScreen2/$itemIndex")
                    }
            )
        }

        Column {
            Text(
                text = myViewModel.dataList2[itemIndex].title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 20.dp)
            )
            Text(
                text = myViewModel.dataList2[itemIndex].startDate,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 20.dp)
            )
            Text(
                text = myViewModel.dataList2[itemIndex].priceInfo,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}