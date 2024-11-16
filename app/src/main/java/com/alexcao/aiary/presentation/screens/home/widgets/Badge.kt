package com.alexcao.aiary.presentation.screens.home.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.alexcao.aiary.R
import com.alexcao.aiary.ui.theme.InterTypography
import com.alexcao.aiary.ui.theme.Secondary

@Composable
fun Badge(
    modifier: Modifier = Modifier,
    @DrawableRes id: Int,
    label: String,
) {
    Button(
        modifier = modifier.height(28.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Secondary),
        border = BorderStroke(1.dp, Secondary),
        shape = RoundedCornerShape(16.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        onClick = { /*TODO*/ }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id),
                contentDescription = "Icon of the expense type",
                modifier = Modifier.size(12.dp),
                tint = Secondary
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                text = label,
                style = InterTypography.bodySmall.copy(
                    color = Secondary
                )
            )
        }
    }
}