package com.alexcao.aiary.presentation.screens.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexcao.aiary.data.repositories.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val expenseRepository: ExpenseRepository
) : ViewModel() {
    private var _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState

    init {
        val dateTime = Calendar.getInstance()
        val month = dateTime.get(Calendar.MONTH)
        viewModelScope.launch {
            val expresses = expenseRepository.getExpenses()
            _homeState.update {
                it.copy(
                    selectedMonth = month,
                    expenses = expresses
                )
            }
        }

    }

    fun onMonthSelected(month: Int) {
        _homeState.update {
            it.copy(selectedMonth = month)
        }
    }

    fun onPageSelected(page: Int) {
        _homeState.update {
            it.copy(selectedPage = page)
        }
    }
}