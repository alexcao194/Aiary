package com.alexcao.dexpense.presentation.screens.settings

import com.alexcao.dexpense.data.models.ExpenseCategory
import com.alexcao.dexpense.data.models.ExpenseSource

data class SettingsState(
    val categories: List<ExpenseCategory> = emptyList(),
    val sources: List<ExpenseSource> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)