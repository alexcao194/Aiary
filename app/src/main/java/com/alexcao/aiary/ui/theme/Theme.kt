package com.alexcao.aiary.ui.theme

import android.app.Activity
import android.graphics.Color
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    primaryContainer = PrimaryBackground,
    secondary = Secondary,
    secondaryContainer = SecondaryBackground,
)

@Composable
fun AiaryTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor = PrimaryBackground.toArgb()
    }

    MaterialTheme(

        colorScheme = LightColorScheme,
        typography = InterTypography,
        content = content
    )
}