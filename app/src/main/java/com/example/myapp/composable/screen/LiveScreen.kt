package com.example.myapp.composable.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.composable.component.LiveItem
import com.example.myapp.data.MyViewModel


@Composable
fun LiveScreen(myViewModel: MyViewModel, navController: NavController) {
    val isLoaded = myViewModel.isLoaded.value
    var searchQuery by remember { mutableStateOf("") }
    var flag:Int by remember { mutableStateOf(0) }

    if (!isLoaded) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Now Loading...", fontSize = 25.sp)
        }

    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top){

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("공연 이름") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
            })
        )

        Row {
            Button(
                onClick = {flag = 1},
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .size(width = 180.dp, height = 50.dp)
                    .padding(top = 10.dp, end = 3.dp)
            ) {
                Text("날짜 빠른순")
            }
            Button(
                onClick = {flag = 2},
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .size(width = 180.dp, height = 50.dp)
                    .padding(top = 10.dp, start = 3.dp)
            ) {
                Text("날짜 늦은순")
            }
        }
        LazyVerticalGrid(modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState()

        ) {
            if (flag == 0) myViewModel.dataList2.sortBy { it.id }
            if (flag == 1) myViewModel.dataList2.sortByDescending { it.id }
            if (flag == 2) myViewModel.dataList2.sortBy { it.id }
            itemsIndexed(myViewModel.dataList2.filter { it.title.contains(searchQuery) }) { index, item ->
                Row(
                    Modifier
                        .background(Color.Transparent)
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    LiveItem(myViewModel, index, navController)
                }
            }
        }
    }
}