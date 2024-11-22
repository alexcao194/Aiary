package com.alexcao.dexpense.presentation.screens.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alexcao.dexpense.R
import com.alexcao.dexpense.data.models.Expense
import com.alexcao.dexpense.presentation.commons.BadgeChip
import com.alexcao.dexpense.utils.extensions.toCurrency

@Composable
fun ExpenseItem(
    modifier: Modifier = Modifier,
    expense: Expense
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
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
            modifier = Modifier.defaultMinSize(minWidth = 100.dp)
        ) {
            Text(
                text = expense.info.label,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = expense.info.amount.toString().toCurrency("VND"),
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            horizontalAlignment = Alignment.End
        ) {
            BadgeChip(
                label = expense.category.name,
                color = expense.category.tint
            )
            Spacer(modifier = Modifier.height(8.dp))
            BadgeChip(
                label = expense.sourceInfo.name,
                color = expense.sourceInfo.tint
            )
        }
    }
}