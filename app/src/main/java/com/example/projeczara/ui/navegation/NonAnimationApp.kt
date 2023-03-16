package com.example.projeczara.ui.navegation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projeczara.ui.detail.DetailScreenContent
import com.example.projeczara.ui.main.MainScreen

@ExperimentalAnimationApi
@Composable
fun NonAnimationApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationRouter.Main.router) {
        composable(
            route = NavigationRouter.Main.router
        ) {
            MainScreen(navController = navController)
        }
        composable(
            route = NavigationRouter.Detail.router + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            val param = entry.arguments?.getInt("id")
            DetailScreenContent(param = param)

        }
    }
}


