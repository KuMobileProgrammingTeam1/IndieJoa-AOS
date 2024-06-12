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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.Retrofit.ArtistData
import com.example.myapp.Retrofit.LiveData
import com.example.myapp.composable.component.HomeItem
import com.example.myapp.data.MyViewModel
import java.util.Locale.filter


@Composable
fun HomeScreen(myViewModel: MyViewModel, navController: NavController) {
    val isLoaded = myViewModel.isLoaded.value
    var searchQuery by remember { mutableStateOf("") }

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
            .padding(start = 5.dp, end = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top){
        Row {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Row {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                    Text("뮤지션 이름", modifier = Modifier.padding(start = 2.dp))
                } },
                modifier = Modifier
                    .padding(bottom = 10.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                })
            )
            Button(
                onClick = { searchQuery = "" },
                modifier = Modifier
                    .size(width = 70.dp, height = 65.dp)
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(4.dp),
            ) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "")
            }
        }
        LazyVerticalGrid(modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState()

        ) {
            itemsIndexed(myViewModel.dataList.filter { it.name.contains(searchQuery) }) { index, item ->
                Row(
                    Modifier
                        .background(Color.Transparent)
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    HomeItem(myViewModel, index, navController)
                }
            }
        }
    }
}