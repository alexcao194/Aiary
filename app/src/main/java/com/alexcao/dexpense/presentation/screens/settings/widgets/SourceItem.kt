package com.alexcao.dexpense.presentation.screens.settings.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alexcao.dexpense.data.models.Source
import com.alexcao.dexpense.utils.extensions.toCurrency

@Composable
fun SourceItem(
    modifier: Modifier = Modifier,
    source: Source
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = source.info.tint,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp)
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = source.info.name,
            style = MaterialTheme.typography.titleSmall.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
            ),
        )

        Text(
            text = source.amount.amount.toString().toCurrency("VND"),
            style = MaterialTheme.typography.titleSmall.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
            ),
        )
    }
}