package com.alexcao.dexpense.presentation.screens.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexcao.dexpense.R
import java.time.Month
import java.util.Locale
import java.time.format.TextStyle


@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    selectedMonth: Int = 0,
    onChangeMonth: (Int) -> Unit = {},
    selectedPage: Int = 0,
    onPageSelected: (Int) -> Unit = {},
    onOpenSettings: () -> Unit = {}
) {
    val lazyRowState = rememberLazyListState()
    LaunchedEffect(Unit) {
        lazyRowState.scrollToItem(selectedMonth)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Welcome back, Alex",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )

            IconButton(onClick = { onOpenSettings() }) {
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = "Settings button",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyRow(
                modifier = Modifier.weight(1f),
                state = lazyRowState,
            ) {
                items(Month.entries.size) { index ->
                    TextButton(onClick = { onChangeMonth(index) }) {
                        Text(
                            text = getMonthName(index),
                            style =
                            if (index == selectedMonth)
                                MaterialTheme.typography.titleMedium
                            else
                                MaterialTheme.typography.titleMedium.copy(
                                    color = MaterialTheme.colorScheme.inversePrimary
                                ),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
        TabRow(
            selectedTabIndex = selectedPage,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.primary,
            indicator = { tabPositions ->
                SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedPage]),
                    height = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            },
        ) {
            val tabIds = listOf(
                R.string.expenses,
                R.string.income,
            )
            tabIds.forEachIndexed { index, tabId ->
                Tab(
                    selected = index == selectedPage,
                    onClick = {
                        onPageSelected(index)
                    },
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringResource(id = tabId),
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        }
    }
}

fun getMonthName(month: Int): String {
    val locale = Locale("en")
    val name = Month.entries[month].getDisplayName(TextStyle.FULL, locale)
    return name
}

@Preview
@Composable
fun HomeHeaderPreview() {
    HomeHeader()
}