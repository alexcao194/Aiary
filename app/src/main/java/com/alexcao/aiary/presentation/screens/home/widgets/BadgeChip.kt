package com.alexcao.aiary.presentation.screens.home.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BadgeChip(
    modifier: Modifier = Modifier,
    color: Color,
    label: String,
    onClick: () -> Unit = {},
    isLight: Boolean = false
) {
    Button(
        modifier = modifier.height(28.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = color,
        ),
        shape = RoundedCornerShape(16.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        onClick = { onClick() }
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(
                color =
                if (isLight)
                    MaterialTheme.colorScheme.onSurface
                else
                    MaterialTheme.colorScheme.onPrimary
            )
        )
    }
}