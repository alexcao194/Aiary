package com.alexcao.aiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.alexcao.aiary.presentation.navigation.AppNavHost
import com.alexcao.aiary.ui.theme.AiaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()
            AiaryTheme {
                AppNavHost(
                    navHostController = navHostController,
                )
            }
        }
    }
}