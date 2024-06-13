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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.composable.component.HomeItem
import com.example.myapp.data.MyViewModel


@Composable
fun HomeScreen(myViewModel: MyViewModel, navController: NavController) {
    val isLoaded = myViewModel.isArtistListLoaded.value
    val size = 20
    val lastPage = myViewModel.artistLastPage
    val pageNum = rememberSaveable {
        mutableStateOf(1)
    }
    val pageNumText = rememberSaveable {
        mutableStateOf(pageNum.value.toString())
    }
    val artistName = rememberSaveable {
        mutableStateOf("")
    }
    val artistNameText = rememberSaveable {
        mutableStateOf(artistName.value)
    }

    LaunchedEffect(key1 = pageNum.value) {
        myViewModel.refreshArtistList(
            page = pageNum.value - 1,
            size = size,
            name = artistName.value
        )
    }

    if (!isLoaded) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Now Loading...", fontSize = 25.sp)
        }
        return
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 5.dp, end = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row {
            OutlinedTextField(
                value = artistNameText.value,
                onValueChange = { artistNameText.value = it },
                label = {
                    Row {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "")
                        Text("뮤지션 이름", modifier = Modifier.padding(start = 2.dp))
                    }
                },
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    artistName.value = artistNameText.value
                    if (pageNum.value == 1) {
                        myViewModel.refreshArtistList(
                            page = pageNum.value - 1,
                            size = size,
                            name = artistName.value
                        )
                    } else {
                        pageNum.value = 1
                        pageNumText.value = pageNum.value.toString()
                    }
                })
            )
        }
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState()
        ) {
            itemsIndexed(myViewModel.artistList) { index, artist ->
                Row(
                    Modifier
                        .background(Color.Transparent)
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    HomeItem(artist, myViewModel, navController)
                }
            }
            item(span = { GridItemSpan(2) }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (pageNum.value > 1) {
                        PageArrowButton(icon = Icons.Filled.KeyboardArrowLeft) {
                            pageNum.value--
                            pageNumText.value = pageNum.value.toString()
                        }
                    }
                    PageTextField(
                        pageNumText.value,
                        lastPage.toString(),
                        onValueChange = {
                            pageNumText.value = it
                        }, onDone = {
                            var num = pageNumText.value.toInt()
                            if (num < 0) num = 0
                            if (num > lastPage) num = lastPage
                            pageNum.value = num
                            pageNumText.value = num.toString()
                        })

                    if (pageNum.value < lastPage) {
                        PageArrowButton(icon = Icons.Filled.KeyboardArrowRight) {
                            pageNum.value++
                            pageNumText.value = pageNum.value.toString()
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun PageArrowButton(icon: ImageVector, onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = Modifier) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Composable
fun PageTextField(
    number: String,
    lastPage: String,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = number,
            onValueChange = {
                onValueChange(it)
            },
            singleLine = true,
            label = { Text(text = lastPage) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onDone() }
            ),
            modifier = Modifier
                //.fillMaxHeight()
                .width(75.dp)
        )
    }
}