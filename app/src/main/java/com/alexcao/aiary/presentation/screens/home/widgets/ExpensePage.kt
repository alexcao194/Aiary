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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexcao.aiary.R
import com.alexcao.aiary.data.models.Category
import com.alexcao.aiary.data.models.ExpanseSource
import com.alexcao.aiary.data.models.Expense
import com.alexcao.aiary.ui.theme.InterTypography
import com.alexcao.aiary.ui.theme.Primary
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@Composable
fun ExpensePage(
    modifier: Modifier = Modifier,
    expenses: List<Expense>,
) {
    val currentDate = LocalDate.now()
    val currentDay = currentDate.dayOfMonth
    val daysInMonth = getAllDaysInMonth(currentDate.monthValue, currentDate.year)
    val lazyColumnState = rememberLazyListState()

    LaunchedEffect(Unit) {
        lazyColumnState.scrollToItem(currentDay)
    }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 4.dp
        ),
        state = lazyColumnState
    ) {
        items(currentDay) { index ->
            val dateFormat = DateTimeFormatter.ofPattern("dd/MMM", Locale("en"))
            val date = dateFormat.format(daysInMonth[index])

            val expensesInDay = expenses.filter { isSameDay(it.date, daysInMonth[index]) }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = date,
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
            for (expense in expensesInDay) {
                ExpenseItem(expense = expense)
            }
        }
    }
}

fun getAllDaysInMonth(month: Int, year: Int): List<LocalDate> {
    val daysInMonth = mutableListOf<LocalDate>()
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.MONTH, month - 1)
    calendar.set(Calendar.YEAR, year)
    val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    for (i in 1..maxDay) {
        calendar.set(Calendar.DAY_OF_MONTH, i)
        daysInMonth.add(LocalDate.of(year, month, i))
    }
    return daysInMonth
}

fun isSameDay(date1: LocalDate, date2: LocalDate): Boolean {
    Log.d("TAG", "isSameDay: $date1, $date2")
    return date1.year == date2.year && date1.month == date2.month && date1.dayOfMonth == date2.dayOfMonth
}

@Composable
@Preview(
    showBackground = true
)
fun ExpensePagePreview() {
    ExpensePage(
        expenses = listOf(
            Expense(
                id = 1,
                date = LocalDate.now(),
                label = "Transcription",
                unit = "VND",
                amount = 100000.0,
                source = ExpanseSource(
                    name = "Transport",
                    drawable = R.drawable.bike
                ),
                category = Category(
                    name = "Transport",
                    drawable = R.drawable.invoices
                )
            )
        )
    )
}