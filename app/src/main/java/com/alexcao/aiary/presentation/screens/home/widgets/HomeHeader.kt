package com.alexcao.aiary.presentation.screens.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexcao.aiary.ui.theme.InterTypography
import com.alexcao.aiary.ui.theme.PrimaryBackground
import java.time.Month
import java.util.Locale
import java.time.format.TextStyle


@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    selectedMonth: Int = 0,
    onChangeMonth: (Int) -> Unit = {},
) {
    val lazyRowState = rememberLazyListState()
    LaunchedEffect(Unit) {
        lazyRowState.scrollToItem(selectedMonth)
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = PrimaryBackground,
            )
    ) {
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
    }
}

fun getMonthName(month: Int) : String {
    val locale = Locale.getDefault()
    val name = Month.entries[month].getDisplayName(TextStyle.FULL, locale)
    return name
}

@Preview
@Composable
fun HomeHeaderPreview() {
    HomeHeader()
}