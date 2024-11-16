package com.alexcao.aiary.presentation.screens.home.widgets

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexcao.aiary.R
import com.alexcao.aiary.ui.theme.InterTypography
import com.alexcao.aiary.ui.theme.Primary
import com.alexcao.aiary.ui.theme.PrimaryBackground
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
) {
    val lazyRowState = rememberLazyListState()
    LaunchedEffect(Unit) {
        lazyRowState.scrollToItem(selectedMonth)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = PrimaryBackground,
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Welcome back, Alex",
                style = InterTypography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(8.dp)
            )

            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = "Settings button",
                    tint = Primary
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
                            style = if (index == selectedMonth) InterTypography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            ) else InterTypography.titleMedium.copy(
                                color = Color.Gray
                            ),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
        TabRow(
            selectedTabIndex = selectedPage,
            containerColor = PrimaryBackground,
            contentColor = Primary,
            indicator = { tabPositions ->
                SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedPage]),
                    height = 2.dp,
                    color = Primary
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
                        text = stringResource(id = tabId),
                        style = InterTypography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

fun getMonthName(month: Int) : String {
    val locale = Locale("en")
    val name = Month.entries[month].getDisplayName(TextStyle.FULL, locale)
    return name
}

@Preview
@Composable
fun HomeHeaderPreview() {
    HomeHeader()
}