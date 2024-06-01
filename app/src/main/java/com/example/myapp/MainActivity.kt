package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapp.composable.screen.GroupDetailScreen
import com.example.myapp.composable.screen.HomeScreen
import com.example.myapp.data.MyViewModel
import com.example.myapp.ui.theme.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "HomeScreen") {


                        val myViewmodel: MyViewModel by viewModels()

                        composable(route = "HomeScreen") {
                            HomeScreen(myViewmodel, navController)
                        }

                        composable(route = "GroupDetailScreen/{index}",
                            arguments = listOf(
                                navArgument(name = "index") {
                                    type = NavType.IntType
                                }
                            )
                        ) { index->
                            val itemIndex = index.arguments?.getInt("index")
                            GroupDetailScreen(myViewmodel ,itemIndex)
                        }

                    }
                }
            }
        }
    }
}

