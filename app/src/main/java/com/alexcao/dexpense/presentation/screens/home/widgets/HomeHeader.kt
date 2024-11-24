package com.alexcao.dexpense.presentation.screens.home.widgets

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexcao.dexpense.R
import com.alexcao.dexpense.ui.theme.expenseColor
import com.alexcao.dexpense.ui.theme.expenseIndicatorColor
import com.alexcao.dexpense.ui.theme.inComeColor
import com.alexcao.dexpense.ui.theme.inComeIndicatorColor
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
    if (selectedMonth != 0) {
        LaunchedEffect(Unit) {
            lazyRowState.scrollToItem(selectedMonth)
        }
    }

    val indicatorColor by animateColorAsState(
        targetValue = if (selectedPage == 0) expenseIndicatorColor else inComeIndicatorColor,
        animationSpec = tween(durationMillis = 300),
        label = "indicatorColor"
    )

    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
            )
            .padding(top = statusBarHeight)
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
                                MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            else
                                MaterialTheme.typography.titleMedium.copy(
                                    color = MaterialTheme.colorScheme.secondary
                                ),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
        TabRow(
            selectedTabIndex = selectedPage,
            indicator = { tabPositions ->
                SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedPage]),
                    height = 2.dp,
                    color = indicatorColor
                )
            },
        ) {
            Tab(
                modifier = Modifier.background(
                  color = expenseColor
                ),
                selected = selectedPage == 0,
                onClick = {
                    onPageSelected(0)
                },
                selectedContentColor = Color.White,
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = stringResource(id = R.string.expenses),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Tab(
                modifier = Modifier.background(
                    color = inComeColor
                ),
                selected = selectedPage == 1,
                onClick = {
                    onPageSelected(1)
                },
                selectedContentColor = Color.White,
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = stringResource(id = R.string.income),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
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