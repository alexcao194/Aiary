package com.alexcao.aiary.presentation.screens.settings

import com.alexcao.aiary.data.models.ExpenseCategory
import com.alexcao.aiary.data.models.ExpenseSource

data class SettingsState(
    val categories: List<ExpenseCategory> = emptyList(),
    val sources: List<ExpenseSource> = emptyList()
)