package com.alexcao.dexpense

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.alexcao.dexpense.presentation.navigation.AppNavHost
import com.alexcao.dexpense.ui.theme.DexpenseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()
            DexpenseTheme {
                AppNavHost(
                    navHostController = navHostController,
                )
            }
        }
    }
}