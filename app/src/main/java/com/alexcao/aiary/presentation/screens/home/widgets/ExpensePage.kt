package com.alexcao.aiary.presentation.screens.home.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexcao.aiary.ui.theme.InterTypography

@Composable
fun ExpensePage() {
    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 4.dp
        )
    ) {
        item {
            Text(
                text = "20/09",
                style = InterTypography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF8F8F8F)
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        item {
            ExpenseItem(label = "Đổ xăng", amount = "100,000 VND")
        }
        item {
            Text(
                text = "Hôm nay",
                style = InterTypography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF8F8F8F)
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        item {
            ExpenseItem(label = "Mua sắm", amount = "500,000 VND")
        }
        item {
            ExpenseItem(label = "Ăn uống", amount = "200,000 VND")
        }
    }
}

@Composable
@Preview(
    showBackground = true
)
fun ExpensePagePreview() {
    ExpensePage()
}