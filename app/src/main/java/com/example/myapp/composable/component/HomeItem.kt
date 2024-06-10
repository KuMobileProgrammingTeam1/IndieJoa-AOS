package com.example.myapp.composable.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
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
fun HomeItem(myViewModel: MyViewModel, itemIndex:Int, navController: NavController) {

    Row {
        if(!myViewModel.dataList[itemIndex].imageUrl.isBlank()){
            AsyncImage(
                model = myViewModel.dataList[itemIndex].imageUrl,
                contentDescription = myViewModel.dataList[itemIndex].name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(175.dp)
                    .clickable {
                        navController.navigate(route = "GroupDetailScreen/$itemIndex")
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
                    .clickable {
                        navController.navigate(route = "GroupDetailScreen/$itemIndex")
                    }
            )
        }

        Column {
            Text(
                text = myViewModel.dataList[itemIndex].name,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 20.dp)
            )
            Text(
                text = myViewModel.dataList[itemIndex].name,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 20.dp)
            )

        }
    }

}