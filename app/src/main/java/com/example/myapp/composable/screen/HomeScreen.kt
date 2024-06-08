package com.example.myapp.composable.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapp.composable.component.HomeItem
import com.example.myapp.data.MyViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(myViewModel: MyViewModel, navController: NavController) {
    val context = LocalContext.current
    val sortType = arrayOf("날짜순", "가격순")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(sortType[0]) }
    var userInput by remember {
        mutableStateOf(TextFieldValue())
    }
    Scaffold(
        topBar = {
            TopAppBar(colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
                title = {
                    Row {
                        OutlinedTextField(
                            value = userInput,
                            onValueChange = {userInput = it},
                            modifier = Modifier.width(150.dp),
                            label = { Text("band name") },
                            placeholder = { Text("search")},
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(1.dp)
                        ) {
                            ExposedDropdownMenuBox(
                                expanded = expanded,
                                onExpandedChange = {
                                    expanded = !expanded
                                }
                            ) {
                                Row {
                                    OutlinedTextField(
                                        value = selectedText,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                                        modifier = Modifier
                                            .menuAnchor()
                                            .width(150.dp),
                                        keyboardActions = KeyboardActions(onDone = {}),
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
                                    )
                                    ExposedDropdownMenu(
                                        expanded = expanded,
                                        onDismissRequest = { expanded = false }
                                    ) {
                                        sortType.forEach { item ->
                                            DropdownMenuItem(
                                                text = { Text(text = item) },
                                                onClick = {
                                                    selectedText = item
                                                    expanded = false
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            LazyColumn (
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxWidth()
            ) {
                if(selectedText == "날짜순") {
                    myViewModel.dataList.sortBy { it.showDate }
                }
                if(selectedText == "가격순") {
                    myViewModel.dataList.sortBy { it.showPrice }
                }
                itemsIndexed(myViewModel.dataList) {index, item ->
                    Row (
                        Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 12.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        HomeItem(myViewModel, index, navController)
                    }
                }
            }
        }
    }
}
