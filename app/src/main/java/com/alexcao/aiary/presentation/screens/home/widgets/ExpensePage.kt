package com.alexcao.aiary.presentation.screens.home.widgets

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexcao.aiary.ui.theme.InterTypography
import com.alexcao.aiary.ui.theme.Primary
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun ExpensePage() {
    val currentTime = Calendar.getInstance()
    Log.d("TAG", "ExpensePage: ${currentTime.get(Calendar.MONTH)}")
    val daysInMonth = getAllDaysInMonth(
        currentTime.get(Calendar.MONTH),
        currentTime.get(Calendar.YEAR)
    )

    val currentDay = currentTime.get(Calendar.DAY_OF_MONTH)

    val lazyColumnState = rememberLazyListState()

    LaunchedEffect(Unit) {
        lazyColumnState.scrollToItem(currentDay)
    }

    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 4.dp
        ),
        state = lazyColumnState
    ) {
        items(currentDay) { index ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = daysInMonth[index],
                    style = InterTypography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF8F8F8F)
                    ),
                )
                IconButton(

                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        tint = Primary,
                        contentDescription = "Add expense in specific day",
                    )
                }
            }
            if (index < 5) {
                for (i in 1..index) {
                    ExpenseItem(label = "Transcription", amount = "100,000 VND")
                }
            }
        }
    }
}

fun getAllDaysInMonth(month: Int, year: Int): List<String> {
    val daysInMonth = mutableListOf<String>()
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month)
    val days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    for (i in 1..days) {
        calendar.set(Calendar.DAY_OF_MONTH, i)
        val simpleDateFormat = SimpleDateFormat("dd/MM", Locale("en"))
        daysInMonth.add(simpleDateFormat.format(calendar.time))
    }
    return daysInMonth
}

@Composable
@Preview(
    showBackground = true
)
fun ExpensePagePreview() {
    ExpensePage()
}