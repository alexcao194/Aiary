package com.alexcao.dexpense.presentation.screens

import com.alexcao.dexpense.data.models.Category
import com.alexcao.dexpense.data.models.Source

data class SharedState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentMonth: Int = 0,
    val categories: List<Category> = emptyList(),
    val sources: List<Source> = emptyList(),
)