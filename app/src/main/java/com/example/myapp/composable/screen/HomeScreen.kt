package com.example.myapp.composable.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
    val isLoaded = myViewModel.isLoaded.value

    val pageNumber = rememberSaveable {
        mutableStateOf(0)
    }
    val lastPage = 9999

    val size = 10

    val pageTextFieldNumber = rememberSaveable {
        mutableStateOf(pageNumber.value.toString())
    }

    LaunchedEffect(key1 = pageNumber.value) {
        myViewModel.UpdateDataList(page = pageNumber.value, size = size)
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
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (pageNumber.value != 0) {
                    PageArrowButton(icon = Icons.Filled.KeyboardArrowLeft) {
                        pageNumber.value--
                        pageTextFieldNumber.value = pageNumber.value.toString()
                    }
                }

                PageTextField(
                    pageTextFieldNumber.value,
                    onValueChange = {
                        pageTextFieldNumber.value = it
                    }, onDone = {
                        pageNumber.value = pageTextFieldNumber.value.toInt()
                    })

                if (pageNumber.value != lastPage) {
                    PageArrowButton(icon = Icons.Filled.KeyboardArrowRight) {
                        pageNumber.value++
                        pageTextFieldNumber.value = pageNumber.value.toString()
                    }
                }
            }
        }
    }
}

@Composable
fun PageArrowButton(icon: ImageVector, onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = Modifier.fillMaxHeight()) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Composable
fun PageTextField(number: String, onValueChange: (String) -> Unit, onDone: () -> Unit) {
    OutlinedTextField(
        value = number,
        onValueChange = {
            onValueChange(it)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { onDone() }
        ),
        modifier = Modifier
            .fillMaxHeight()
            .width(100.dp)
    )
}