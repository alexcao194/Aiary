package com.alexcao.dexpense.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alexcao.dexpense.R
import com.alexcao.dexpense.data.models.Expense
import com.alexcao.dexpense.data.models.ExpenseType
import com.alexcao.dexpense.presentation.navigation.Route
import com.alexcao.dexpense.presentation.screens.SharedViewModel
import com.alexcao.dexpense.presentation.screens.home.widgets.ExpenseDialog
import com.alexcao.dexpense.presentation.screens.home.widgets.ExpensePage
import com.alexcao.dexpense.presentation.screens.home.widgets.HomeHeader
import com.alexcao.dexpense.utils.extensions.encode
import java.time.LocalDate

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel,
    navHostController: NavHostController
) {
    val state = homeViewModel.state.collectAsState().value
    val sharedState = sharedViewModel.state.collectAsState().value
    val error = state.error ?: sharedState.error
    val selectedPage = state.selectedPage
    val currentMonth = sharedState.currentMonth
    val expenses = state.expenses
    val sources = sharedState.sources
    val categories = sharedState.categories
    var isDialogOpen by rememberSaveable { mutableStateOf(false) }
    var currentDate by remember {
        mutableStateOf(LocalDate.now())
    }
    var currentExpense: Expense? = null
    var expenseType: ExpenseType = ExpenseType.EXPENSE
    val message = stringResource(R.string.please_add_at_least_one_category_and_source)

    val pagerState = rememberPagerState(
        initialPage = selectedPage,
        pageCount = { 2 }
    )

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(selectedPage) {
        pagerState.animateScrollToPage(selectedPage)
    }

    LaunchedEffect(Unit) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            homeViewModel.selectPage(page)
        }
    }

    LaunchedEffect(currentMonth) {
        homeViewModel.selectMonth(currentMonth)
    }

    LaunchedEffect(error) {
        error?.let {
            snackbarHostState.showSnackbar(it)
            homeViewModel.clearError()
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { padding ->
        Column(
            modifier = Modifier.consumeWindowInsets(padding)
        ) {
            HomeHeader(
                selectedMonth = currentMonth,
                onChangeMonth = { month ->
                    sharedViewModel.selectMonth(month)
                },
                selectedPage = selectedPage,
                onPageSelected = { page ->
                    homeViewModel.selectPage(page)
                },
                onOpenSettings = {
                    navHostController.navigate(Route.SETTINGS.route)
                }
            )
            HorizontalPager(state = pagerState) { page ->
                when (page) {
                    0 -> ExpensePage(
                        expenses = expenses.filter { it.category.type == ExpenseType.EXPENSE },
                        onAddExpense = { date ->
                            if (
                                categories.any { it.type == ExpenseType.EXPENSE }
                                && sources.isNotEmpty()
                            ) {
                                isDialogOpen = true
                                currentDate = date
                                currentExpense = null
                                expenseType = ExpenseType.EXPENSE
                            } else {
                                navHostController.navigate(
                                    "${Route.SETTINGS.route}?message=${message.encode()}"
                                )
                            }
                        },
                        onPickExpense = { expenses ->
                            isDialogOpen = true
                            currentDate = expenses.info.date
                            currentExpense = expenses
                        },
                        expenseType = ExpenseType.EXPENSE,
                        currentMonth = currentMonth,
                        onOpenStatistics = {
                            navHostController.navigate(Route.STATISTICS.route)
                        }
                    )

                    1 -> ExpensePage(
                        expenses = expenses.filter { it.category.type == ExpenseType.INCOME },
                        onAddExpense = { date ->
                            if (
                                categories.any { it.type == ExpenseType.INCOME }
                                && sources.isNotEmpty()
                            ) {
                                isDialogOpen = true
                                currentDate = date
                                currentExpense = null
                                expenseType = ExpenseType.INCOME
                            } else {
                                navHostController.navigate(
                                    "${Route.SETTINGS.route}?message=${message.encode()}"
                                )
                            }
                        },
                        onPickExpense = { expenses ->
                            isDialogOpen = true
                            currentDate = expenses.info.date
                            currentExpense = expenses
                        },
                        expenseType = ExpenseType.INCOME,
                        currentMonth = currentMonth,
                        onOpenStatistics = {
                            navHostController.navigate(Route.STATISTICS.route)
                        }
                    )
                }
            }
        }

        if (isDialogOpen) {
            ExpenseDialog(
                onDismissRequest = {
                    isDialogOpen = false
                },
                onSave = { expense ->
                    homeViewModel.saveExpense(expense)
                },
                onDelete = { expense ->
                    homeViewModel.deleteExpense(expense)
                },
                onUpdate = { expense ->
                    homeViewModel.updateExpense(expense)
                },
                initialExpense = currentExpense,
                localDate = currentDate,
                sources = sources,
                categories = categories.filter {
                    it.type == (currentExpense?.category?.type ?: expenseType)
                },
                expenseType = expenseType
            )
        }
    }
}