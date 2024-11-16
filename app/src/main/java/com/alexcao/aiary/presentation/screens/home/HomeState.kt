package com.alexcao.aiary.presentation.screens.home

import com.alexcao.aiary.data.models.Expense

data class HomeState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedPage: Int = 0,
    val selectedMonth: Int = 0,
    val expenses: List<Expense> = emptyList(),
)