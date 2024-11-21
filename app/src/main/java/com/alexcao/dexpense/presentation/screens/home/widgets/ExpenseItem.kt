package com.alexcao.dexpense.presentation.screens.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alexcao.dexpense.R
import com.alexcao.dexpense.data.models.Expense

@Composable
fun ExpenseItem(
    modifier: Modifier = Modifier,
    expense: Expense
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(vertical = 4.dp)
            .background(
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFE9E9E9)
            )
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = expense.info.label,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Black,
                )
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.coin),
                    contentDescription = "Coin icon",
                    modifier = Modifier.size(16.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = expense.info.amount.toString(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
        Row {
            BadgeChip(
                label = expense.category.name,
                color = expense.category.tint
            )
            Spacer(modifier = Modifier.width(8.dp))
            BadgeChip(
                label = expense.sourceInfo.name,
                color = expense.sourceInfo.tint
            )
        }
    }
}