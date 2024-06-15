package com.example.myapp.composable.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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

    val isLoaded = myViewModel.isLiveListLoaded.value
    val size = 20
    val lastPage = myViewModel.liveLastPage
    var checkedDateSort by rememberSaveable { mutableStateOf(false) }

    val pageNum = rememberSaveable {
        mutableStateOf(1)
    }
    val pageNumText = rememberSaveable {
        mutableStateOf(pageNum.value.toString())
    }
    val liveName = rememberSaveable {
        mutableStateOf("")
    }
    val liveNameText = rememberSaveable {
        mutableStateOf(liveName.value)
    }
    val liveSort = rememberSaveable {
        mutableIntStateOf(0)
    }

    LaunchedEffect(key1 = pageNum.value, key2 = liveSort) {
        myViewModel.refreshLiveList(
            page = pageNum.value - 1,
            size = size,
            name = liveName.value,
            sort = liveSort.value
        )
    }

    if (!isLoaded) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Now Loading...", fontSize = 25.sp, color = Color.White)
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedTextColor = Color.White
                ),
                value = liveNameText.value,
                onValueChange = { liveNameText.value = it },
                label = {
                    Row {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "")
                        Text("공연 이름", modifier = Modifier.padding(start = 2.dp))
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    liveName.value = liveNameText.value
                    liveSort.value = liveSort.value
                    if (pageNum.value == 1) {
                        myViewModel.refreshLiveList(
                            page = pageNum.value - 1,
                            size = size,
                            name = liveName.value,
                            sort = liveSort.value
                        )
                    } else {
                        pageNum.value = 1
                        pageNumText.value = pageNum.value.toString()
                    }
                })
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Switch(
                    checked = checkedDateSort,
                    onCheckedChange = {
                        checkedDateSort = it
                        if (liveSort.value == 0) liveSort.value++
                        else liveSort.value--
                        liveName.value = liveNameText.value
                        liveSort.value = liveSort.value
                        if (pageNum.value == 1) {
                            myViewModel.refreshLiveList(
                                page = pageNum.value - 1,
                                size = size,
                                name = liveName.value,
                                sort = liveSort.value
                            )
                        } else {
                            pageNum.value = 1
                            pageNumText.value = pageNum.value.toString()
                        }
                    }
                )
                Text(
                    color = Color.White,
                    text = "최신등록",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState()

        ) {
            itemsIndexed(myViewModel.liveList) { index, live ->
                Row(
                    Modifier
                        .background(Color.Transparent)
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    LiveItem(live, myViewModel, navController)
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
                            var num: Int = 0
                            try {
                                num = pageNumText.value.toInt()
                            } catch (e: Exception) {
                                pageNumText.value = "1"
                                num = pageNumText.value.toInt()
                            }
                            if (num <= 0) num = 1
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