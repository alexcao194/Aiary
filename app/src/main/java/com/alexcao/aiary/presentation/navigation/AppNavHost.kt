package com.alexcao.aiary.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alexcao.aiary.presentation.screens.home.HomeScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startRoute: Route = Route.HOME,
) {
    NavHost(
        navController = navHostController,
        startDestination = startRoute.route,
        modifier = modifier
    ) {
        composable(Route.HOME.route) {
            HomeScreen(
//                navHostController = navHostController,
            )
        }
    }
}