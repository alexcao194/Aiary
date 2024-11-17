package com.alexcao.aiary.presentation.screens.settings

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexcao.aiary.data.models.ExpenseCategory
import com.alexcao.aiary.data.repositories.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val expenseRepository: ExpenseRepository
) : ViewModel() {
    private val _state: MutableStateFlow<SettingsState> = MutableStateFlow(SettingsState())
    val state: MutableStateFlow<SettingsState> = _state

    init {
        viewModelScope.launch {
            expenseRepository.getExpensesCategory().collect { categories ->
                _state.update {
                    it.copy(categories = categories)
                }
            }

            expenseRepository.getExpensesSource().collect { sources ->
                _state.update {
                    it.copy(sources = sources)
                }
            }
        }
    }

    fun onSaveCategory(value: String, color: Color) {
        viewModelScope.launch {
//            expenseRepository.addCategory(ExpenseCategory(name = value, tint = color, expenseId = 0))
        }
    }
}