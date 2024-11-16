package com.alexcao.aiary.presentation.screens.home.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexcao.aiary.ui.theme.InterTypography
import com.alexcao.aiary.ui.theme.Secondary

@Composable
fun Badge(
    modifier: Modifier = Modifier,
    label: String,
) {
    Button(
        modifier = modifier.height(28.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Secondary
        ),
        border = BorderStroke(1.dp, Secondary),
        shape = RoundedCornerShape(16.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        onClick = { /*TODO*/ }
    ) {
        Text(
            text = label,
            style = InterTypography.bodySmall.copy(
                color = Secondary
            )
        )
    }
}