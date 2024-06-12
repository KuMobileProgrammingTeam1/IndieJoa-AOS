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
fun HomeItem(myViewModel: MyViewModel, itemIndex: Int, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = myViewModel.dataList[itemIndex].imageUrl,
            contentDescription = myViewModel.dataList[itemIndex].name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(175.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    navController.navigate(route = "GroupDetailScreen/$itemIndex")
                },
            error = painterResource(id = R.drawable.defaultimg)
        )


        Column {
            Text(
                text = myViewModel.dataList[itemIndex].name,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}