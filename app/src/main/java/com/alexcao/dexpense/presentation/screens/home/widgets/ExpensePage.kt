package com.alexcao.dexpense.presentation.screens.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alexcao.dexpense.R
import com.alexcao.dexpense.data.models.Expense
import com.alexcao.dexpense.data.models.ExpenseType
import com.alexcao.dexpense.ui.theme.expenseColor
import com.alexcao.dexpense.ui.theme.inComeColor
import com.alexcao.dexpense.utils.extensions.daysInMonth
import com.alexcao.dexpense.utils.extensions.isSameDay
import com.alexcao.dexpense.utils.extensions.toCurrency
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ExpensePage(
    modifier: Modifier = Modifier,
    expenses: List<Expense>,
    onAddExpense: (LocalDate) -> Unit = {},
    onPickExpense: (Expense) -> Unit = {},
    expenseType: ExpenseType
) {
    val currentDate = LocalDate.now()
    val currentDay = currentDate.dayOfMonth
    val daysInMonth = currentDate.daysInMonth
    val lazyColumnState = rememberLazyListState()

    val units = expenses.map { expense -> expense.sourceInfo.unit }.toSet()

    LaunchedEffect(Unit) {
        lazyColumnState.scrollToItem(currentDay)
    }

    Column(
        modifier = modifier,
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f),
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
                        text = daysInMonth[index].format(
                            DateTimeFormatter.ofPattern(
                                "EEE, MMM dd",
                                Locale.US
                            )
                        ),
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
                    ExpenseItem(
                        modifier = Modifier
                            .clickable { onPickExpense(expense) },
                        expense = expense
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    if (expenseType == ExpenseType.EXPENSE) expenseColor else inComeColor
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.total),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onPrimary // Text color
            )
            if (units.isEmpty()) {
                Text(
                    text = stringResource(R.string.no_available_records),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onPrimary // Text color
                )
            }
            for (unit in units) {
                val total = expenses.filter { it.sourceInfo.unit == unit }.sumOf { it.info.amount }
                Text(
                    text = total.toString().toCurrency(unit),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onPrimary // Text color
                )
            }
        }
    }
}
