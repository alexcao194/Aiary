package com.alexcao.aiary.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.alexcao.aiary.presentation.screens.home.widgets.ExpenseDialog
import com.alexcao.aiary.presentation.screens.home.widgets.ExpensePage
import com.alexcao.aiary.presentation.screens.home.widgets.HomeHeader
import com.alexcao.aiary.ui.theme.AiaryTheme
import com.alexcao.aiary.ui.theme.PrimaryBackground

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val homeState = homeViewModel.homeState.collectAsState().value
    val selectedPage = homeState.selectedPage
    val selectedMonth = homeState.selectedMonth
    val expenses = homeState.expenses
    var isDialogOpen by rememberSaveable { mutableStateOf(false) }

    val pagerState = rememberPagerState(
        initialPage = selectedPage,
        pageCount = { 2 }
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
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            HomeHeader(
                selectedMonth = selectedMonth,
                onChangeMonth = { month ->
                    homeViewModel.onMonthSelected(month)
                },
                selectedPage = selectedPage,
                onPageSelected = { page ->
                    homeViewModel.onPageSelected(page)
                }
            )
            HorizontalPager(state = pagerState) { page ->
                when (page) {
                    0 -> ExpensePage(
                        expenses = expenses,
                        onAddExpense = {
                            isDialogOpen = true
                        }
                    )

                    1 -> ExpensePage(expenses = expenses)
                }
            }
        }

        if (isDialogOpen) {
            ExpenseDialog(
                onDismissRequest = {
                    isDialogOpen = false
                },
                onSave = {
                    homeViewModel.onSaveExpense()
                },
                expense = null
            )
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