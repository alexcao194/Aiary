package com.alexcao.dexpense.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alexcao.dexpense.R
import com.alexcao.dexpense.data.models.Expense
import com.alexcao.dexpense.data.models.ExpenseType
import com.alexcao.dexpense.presentation.navigation.Route
import com.alexcao.dexpense.presentation.screens.home.widgets.ExpenseDialog
import com.alexcao.dexpense.presentation.screens.home.widgets.ExpensePage
import com.alexcao.dexpense.presentation.screens.home.widgets.HomeHeader
import com.alexcao.dexpense.ui.theme.DexpenseTheme
import com.alexcao.dexpense.utils.extensions.encode
import java.time.LocalDate

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val state = homeViewModel.state.collectAsState().value
    val error = state.error
    val selectedPage = state.selectedPage
    val selectedMonth = state.selectedMonth
    val expenses = state.expenses
    val sources = state.sources
    val categories = state.categories
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
            homeViewModel.onPageSelected(page)
        }
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
                        expenseType = ExpenseType.EXPENSE
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
                        expenseType = ExpenseType.INCOME
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
                    homeViewModel.onSaveExpense(expense)
                },
                onDelete = { expense ->
                    homeViewModel.onDeleteExpense(expense)
                },
                onUpdate = { expense ->
                    homeViewModel.onUpdateExpense(expense)
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