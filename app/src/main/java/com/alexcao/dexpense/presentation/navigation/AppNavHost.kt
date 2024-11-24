package com.alexcao.dexpense.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alexcao.dexpense.presentation.screens.SharedViewModel
import com.alexcao.dexpense.presentation.screens.home.HomeScreen
import com.alexcao.dexpense.presentation.screens.settings.SettingsScreen
import com.alexcao.dexpense.presentation.screens.statistics.StatisticsScreen
import com.alexcao.dexpense.utils.extensions.decode

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
                navHostController = navHostController,
                sharedViewModel = hiltViewModel(),
            )
        }
        composable("${Route.SETTINGS.route}?message={message}") {
            val message = it.arguments?.getString("message")?.decode()
            SettingsScreen(
                navHostController = navHostController,
                message = message,
                sharedViewModel = hiltViewModel(),
            )
        }
        composable(Route.STATISTICS.route) {
            StatisticsScreen(
                navHostController = navHostController,
                sharedViewModel = hiltViewModel(),
            )
        }
    }
}