package com.alexcao.aiary.presentation.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alexcao.aiary.ui.theme.InterTypography
import com.alexcao.aiary.ui.theme.Primary
import com.alexcao.aiary.ui.theme.PrimaryBackground
import com.alexcao.aiary.utils.extensions.toTitleCase

@Composable
fun AppHeader(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = PrimaryBackground,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = navHostController.currentDestination?.route
                ?.substringAfterLast("/")?.toTitleCase()
                ?: "Unknown",
            style = InterTypography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.padding(8.dp)
        )

        // check can pop
        if (navHostController.previousBackStackEntry != null) {
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close button",
                    tint = Primary
                )
            }
        }
    }
}