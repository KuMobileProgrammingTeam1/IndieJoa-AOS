package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapp.composable.screen.GroupDetailScreen
import com.example.myapp.composable.screen.GroupDetailScreen2
import com.example.myapp.composable.screen.HomeScreen
import com.example.myapp.composable.screen.LiveScreen
import com.example.myapp.data.MyViewModel
import com.example.myapp.ui.theme.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            Scaffold(
                bottomBar = {
                    BottomAppBar(
                        content = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Button(
                                    onClick = { navController.navigate("HomeScreen") },
                                    shape = RoundedCornerShape(4.dp),
                                    modifier = Modifier
                                        .size(width = 180.dp, height = 50.dp)
                                        .padding(start = 3.dp)
                                    ) {
                                    Text("뮤지션")
                                }
                                Button(
                                    onClick = { navController.navigate("LiveScreen") },
                                    shape = RoundedCornerShape(4.dp),
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
                            .padding(16.dp),
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

                                NavHost(navController = navController, startDestination = "HomeScreen") {
                                    composable(route = "HomeScreen") {
                                        HomeScreen(myViewModel, navController)
                                    }

                                    composable(route = "LiveScreen") {
                                        LiveScreen(myViewModel, navController)
                                    }

                                    composable(
                                        route = "GroupDetailScreen/{index}",
                                        arguments = listOf(
                                            navArgument(name = "index") {
                                                type = NavType.IntType
                                            }
                                        )
                                    ) { backStackEntry ->
                                        val itemIndex = backStackEntry.arguments?.getInt("index")
                                        GroupDetailScreen(myViewModel, itemIndex)
                                    }

                                    composable(
                                        route = "GroupDetailScreen2/{index}",
                                        arguments = listOf(
                                            navArgument(name = "index") {
                                                type = NavType.IntType
                                            }
                                        )
                                    ) { backStackEntry ->
                                        val itemIndex = backStackEntry.arguments?.getInt("index")
                                        GroupDetailScreen2(myViewModel, itemIndex)
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