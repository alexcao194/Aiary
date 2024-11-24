package com.alexcao.dexpense.presentation.screens.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alexcao.dexpense.presentation.commons.AppHeader

@Composable
fun StatisticsScreen(
    modifier: Modifier = Modifier,
    settingsViewModel: StatisticsViewModel = hiltViewModel(),
    navHostController: NavHostController,
) {
    val state = settingsViewModel.state.collectAsState().value

    Scaffold(
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier.consumeWindowInsets(padding)
        ) {
            AppHeader(
                navHostController = navHostController
            )
            Text(text = "Statistics")
        }
    }
}