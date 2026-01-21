package com.example.jetpack3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val sharedViewModel: ProductViewModel = viewModel()

            NavHost(
                navController = navController,
                startDestination = "welcome"
            ){
                composable("welcome"){
                    WelcomeScreen(
                        onStartClicked = {
                            navController.navigate("home")
                        }
                    )
                }

                composable("home") {
                    HomeScreen(
                        navController = navController,
                        viewModel = sharedViewModel
                    )
                }

                composable(
                    route = "details/{productId}",
                    arguments = listOf(navArgument("productId") { type = NavType.StringType }),
                    enterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { fullWidth -> fullWidth },
                            animationSpec = tween(400)
                        )
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { fullWidth -> fullWidth },
                            animationSpec = tween(400)
                        )
                    }
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("productId") ?: ""

                    DetailScreen(
                        productId = id,
                        navController = navController
                    )
                }

                composable("cart"){
                    CartScreen(
                        navController = navController,
                        viewModel = sharedViewModel
                    )
                }

                composable("checkout") {
                    CheckoutScreen(
                        navController = navController,
                        viewModel = sharedViewModel
                    )
                }
            }
        }
    }
}