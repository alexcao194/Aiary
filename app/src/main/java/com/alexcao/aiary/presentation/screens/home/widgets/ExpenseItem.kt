package com.alexcao.aiary.presentation.screens.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexcao.aiary.R
import com.alexcao.aiary.ui.theme.InterTypography
import com.alexcao.aiary.ui.theme.Secondary

@Composable
fun ExpenseItem(
    modifier: Modifier = Modifier,
    label: String,
    amount: String,
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
                text = label,
                style = InterTypography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
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
                    tint = Secondary
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = amount,
                    style = InterTypography.bodySmall.copy(
                        color = Secondary
                    )
                )
            }
        }
        Row {
            Badge(id = R.drawable.bike, label = "Transport")
            Spacer(modifier = Modifier.padding(8.dp))
            Badge(id = R.drawable.invoices, label = "Cash")
        }
    }
}

@Composable
@Preview
fun ExpenseItemPreview() {
    ExpenseItem(
        label = "Gửi xe",
        amount = "3,000 đ"
    )
}