package com.example.myapp.composable.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.data.MyViewModel

@Composable
fun HomeItem(myViewModel: MyViewModel, itemIndex:Int, navController: NavController) {
    Row {
        Image(
            painter = painterResource(id = myViewModel.dataList[itemIndex].bandImg),
            contentDescription = myViewModel.dataList[itemIndex].bandName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(175.dp)
                .clickable {
                    navController.navigate(route = "GroupDetailScreen/$itemIndex")
                }
        )
        Column {
            Text(
                text = myViewModel.dataList[itemIndex].bandName,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 20.dp)
            )
            Text(
                text = myViewModel.dataList[itemIndex].showName,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 20.dp)
            )

        }
    }
}