package com.alexcao.dexpense.presentation.screens.home

import com.alexcao.dexpense.data.models.Expense
import com.alexcao.dexpense.data.models.ExpenseCategory
import com.alexcao.dexpense.data.models.ExpenseSource

data class HomeState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedPage: Int = 0,
    val selectedMonth: Int = 0,
    val expenses: List<Expense> = emptyList(),
    val categories: List<ExpenseCategory> = emptyList(),
    val sources: List<ExpenseSource> = emptyList(),
)