package com.alexcao.dexpense.presentation.screens.settings

import com.alexcao.dexpense.data.models.Category
import com.alexcao.dexpense.data.models.Source
import com.alexcao.dexpense.data.models.SourceInfo

data class SettingsState(
    val loading: Boolean = false,
    val error: String? = null
)
