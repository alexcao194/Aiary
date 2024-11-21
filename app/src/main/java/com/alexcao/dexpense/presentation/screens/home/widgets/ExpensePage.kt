package com.alexcao.dexpense.presentation.screens.home.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexcao.dexpense.data.models.Expense
import com.alexcao.dexpense.utils.extensions.daysInMonth
import com.alexcao.dexpense.utils.extensions.isSameDay
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ExpensePage(
    modifier: Modifier = Modifier,
    expenses: List<Expense>,
    onAddExpense: (LocalDate) -> Unit = {}
) {
    val currentDate = LocalDate.now()
    val currentDay = currentDate.dayOfMonth
    val daysInMonth = currentDate.daysInMonth
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
            val expensesInDay = expenses.filter { it.info.date.isSameDay(daysInMonth[index]) }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = daysInMonth[index].format(DateTimeFormatter.ofPattern("EEE, MMM dd", Locale.US)),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { onAddExpense(daysInMonth[index]) }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        tint = MaterialTheme.colorScheme.primary,
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
