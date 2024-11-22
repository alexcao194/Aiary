package com.alexcao.dexpense.presentation.screens.home

import com.alexcao.dexpense.data.models.Expense
import com.alexcao.dexpense.data.models.Category
import com.alexcao.dexpense.data.models.Source
import com.alexcao.dexpense.data.models.SourceInfo

data class HomeState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedPage: Int = 0,
    val selectedMonth: Int = 0,
    val expenses: List<Expense> = emptyList(),
    val categories: List<Category> = emptyList(),
    val sources: List<Source> = emptyList(),
)