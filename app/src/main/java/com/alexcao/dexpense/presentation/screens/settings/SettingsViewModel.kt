package com.alexcao.dexpense.presentation.screens.settings

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexcao.dexpense.core.Resource
import com.alexcao.dexpense.data.models.ExpenseCategory
import com.alexcao.dexpense.data.models.ExpenseSource
import com.alexcao.dexpense.data.repositories.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val expenseRepository: ExpenseRepository
) : ViewModel() {
    private val _state: MutableStateFlow<SettingsState> = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> = _state

    init {
        viewModelScope.launch {
            expenseRepository.getExpensesCategory().collect { categories ->
                _state.update {
                    it.copy(categories = categories)
                }
            }
        }

        viewModelScope.launch {
            expenseRepository.getExpensesSource().collect { sources ->
                _state.update {
                    it.copy(sources = sources)
                }
            }
        }
    }

    fun saveCategory(category: ExpenseCategory) {
        viewModelScope.launch {
            expenseRepository.addCategory(category).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {}
                    is Resource.Error -> {
                        _state.update { it.copy(error = resource.message) }
                    }
                }
            }
        }
    }

    fun updateCategory(category: ExpenseCategory) {
        viewModelScope.launch {
            expenseRepository.updateCategory(category).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {}
                    is Resource.Error -> {
                        _state.update { it.copy(error = resource.message) }
                    }
                }
            }
        }
    }

    fun deleteCategory(category: ExpenseCategory) {
        viewModelScope.launch {
            expenseRepository.deleteCategory(category).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {}
                    is Resource.Error -> {
                        _state.update { it.copy(error = resource.message) }
                    }
                }
            }
        }
    }

    fun updateSource(source: ExpenseSource) {
        viewModelScope.launch {
            expenseRepository.updateSource(source).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {}
                    is Resource.Error -> {
                        _state.update { it.copy(error = resource.message) }
                    }
                }
            }
        }
    }

    fun saveSource(source: ExpenseSource) {
        viewModelScope.launch {
            expenseRepository.addSource(source).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {}
                    is Resource.Error -> {
                        _state.update { it.copy(error = resource.message) }
                    }
                }
            }
        }
    }

    fun deleteSource(source: ExpenseSource) {
        viewModelScope.launch {
            expenseRepository.deleteSource(source).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {}
                    is Resource.Error -> {
                        _state.update { it.copy(error = resource.message) }
                    }
                }
            }
        }
    }

    fun clearError() {
        _state.update { it.copy(error = null) }
    }
}