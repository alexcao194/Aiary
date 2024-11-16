package com.alexcao.aiary.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alexcao.aiary.presentation.screens.home.widgets.ExpensePage
import com.alexcao.aiary.presentation.screens.home.widgets.HomeHeader
import com.alexcao.aiary.ui.theme.AiaryTheme
import com.alexcao.aiary.ui.theme.InterTypography
import com.alexcao.aiary.ui.theme.Primary
import com.alexcao.aiary.ui.theme.PrimaryBackground

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(),
) {
    val homeState = homeViewModel.homeState.collectAsState().value
    val selectedPage = homeState.selectedPage
    val selectedMonth = homeState.selectedMonth

    val pagerState = rememberPagerState(
        initialPage = selectedPage,
        pageCount = {2}
    )

    LaunchedEffect(selectedPage) {
        pagerState.animateScrollToPage(selectedPage)
    }

    LaunchedEffect(selectedPage) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            homeViewModel.onPageSelected(page)
        }
    }


    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                containerColor = PrimaryBackground,
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Button to add new record"
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            HomeHeader(
                selectedMonth = selectedMonth,
                onChangeMonth = { month ->
                    homeViewModel.onMonthSelected(month)
                },
            )
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
                val tabs = listOf("In", "Out")
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = index == selectedPage,
                        onClick = {
                            homeViewModel.onPageSelected(index)
                        },
                    ) {
                        Text(
                            text = tab,
                            style = InterTypography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                            ),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
            HorizontalPager(state = pagerState) { page ->
                when (page) {
                    0 -> ExpensePage()
                    1 -> ExpensePage()
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    AiaryTheme {
        HomeScreen()
    }
}