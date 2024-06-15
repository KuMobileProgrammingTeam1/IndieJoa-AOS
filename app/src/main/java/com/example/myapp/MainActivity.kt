package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp.composable.screen.GroupDetailScreen
import com.example.myapp.composable.screen.HomeScreen
import com.example.myapp.composable.screen.LiveDetailScreen
import com.example.myapp.composable.screen.LiveScreen
import com.example.myapp.data.MyViewModel
import com.example.myapp.stage.AddressViewModel
import com.example.myapp.ui.theme.MyAppTheme
import com.example.myapp.ui.theme.bottomBarButtonContainerColor
import com.example.myapp.ui.theme.bottomBarContainerColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            var isHomeSelected by remember { mutableStateOf(true) }
            var isLiveSelected by remember { mutableStateOf(false) }

            Scaffold(
                bottomBar = {
                    BottomAppBar(
                        containerColor = bottomBarContainerColor,
                        content = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Button(
                                    onClick = {
                                        navController.navigate("HomeScreen")
                                        isHomeSelected = true
                                        isLiveSelected = false
                                    },
                                    shape = RoundedCornerShape(8.dp),
                                    colors = if (isHomeSelected) {
                                        ButtonDefaults.buttonColors(
                                            containerColor = bottomBarButtonContainerColor,
                                            contentColor = bottomBarContainerColor
                                        )
                                    } else {
                                        ButtonDefaults.buttonColors(
                                            containerColor = Color.Gray,
                                            contentColor = bottomBarContainerColor
                                        )
                                    },
                                    modifier = Modifier
                                        .size(width = 180.dp, height = 50.dp)
                                        .padding(start = 3.dp)
                                ) {
                                    Text("뮤지션")
                                }
                                Button(
                                    onClick = {
                                        navController.navigate("LiveScreen")
                                        isHomeSelected = false
                                        isLiveSelected = true
                                    },
                                    shape = RoundedCornerShape(8.dp),
                                    colors = if (isLiveSelected) {
                                        ButtonDefaults.buttonColors(
                                            containerColor = bottomBarButtonContainerColor,
                                            contentColor = bottomBarContainerColor
                                        )
                                    } else {
                                        ButtonDefaults.buttonColors(
                                            containerColor = Color.Gray,
                                            contentColor = bottomBarContainerColor
                                        )
                                    },
                                    modifier = Modifier
                                        .size(width = 180.dp, height = 50.dp)
                                        .padding(start = 3.dp)
                                ) {
                                    Text("공연")
                                }
                            }
                        }
                    )
                },
                content = { padding ->
                    Column(
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxSize()
                            .background(Color(0xFF263244)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        MyAppTheme {
                            // A surface container using the 'background' color from the theme
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = Color.Transparent
                            ) {
                                val myViewModel: MyViewModel by viewModels()
                                val addressViewModel: AddressViewModel by viewModels()

                                NavHost(
                                    navController = navController,
                                    startDestination = "HomeScreen"
                                ) {
                                    composable(route = "HomeScreen") {
                                        HomeScreen(myViewModel, navController)
                                    }

                                    composable(route = "LiveScreen") {
                                        LiveScreen(myViewModel, navController)
                                    }

                                    composable(route = "GroupDetailScreen") {
                                        GroupDetailScreen(myViewModel)
                                    }
                                    composable(route = "LiveDetailScreen") {
                                        LiveDetailScreen(myViewModel, addressViewModel)
                                    }
                                }

                            }
                        }
                    }
                }
            )
        }
    }
}