package com.example.myapp.composable.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.composable.component.HomeItem
import com.example.myapp.data.MyViewModel


@Composable
fun HomeScreen(myViewModel: MyViewModel, navController: NavController) {
    val isLoaded = myViewModel.isLoaded.value
    if (!isLoaded) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Now Loading...", fontSize = 25.sp)
        }

    }

    LazyColumn(
        modifier = Modifier
            //.background(color = Color.White)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        itemsIndexed(myViewModel.dataList) { index, item ->
            Row(
                Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                HomeItem(myViewModel, index, navController)
            }
        }
    }
}