package com.alexcao.dexpense.presentation.screens.settings

import com.alexcao.dexpense.data.models.Category
import com.alexcao.dexpense.data.models.Source
import com.alexcao.dexpense.data.models.SourceInfo

data class SettingsState(
    val categories: List<Category> = emptyList(),
    val sources: List<Source> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)