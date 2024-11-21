package com.alexcao.dexpense.presentation.screens.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexcao.dexpense.core.Resource
import com.alexcao.dexpense.data.models.Expense
import com.alexcao.dexpense.data.repositories.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val expenseRepository: ExpenseRepository
) : ViewModel() {
    private var _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        val dateTime = Calendar.getInstance()
        _state.update {
            it.copy(selectedMonth = dateTime.get(Calendar.MONTH))
        }

        val month = dateTime.get(Calendar.MONTH) + 1
        viewModelScope.launch {
            expenseRepository.getExpenses(month).collect { expense ->
                _state.update {
                    it.copy(expenses = expense)
                }
            }
        }

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

    fun onMonthSelected(month: Int) {
        _state.update {
            it.copy(selectedMonth = month)
        }
    }

    fun onPageSelected(page: Int) {
        _state.update {
            it.copy(selectedPage = page)
        }
    }

    fun onSaveExpense(expense: Expense) {
        viewModelScope.launch {
            expenseRepository.addExpense(expense = expense).collect { resource ->
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
}