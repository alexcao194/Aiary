package com.alexcao.aiary.presentation.screens.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.util.Calendar

class HomeViewModel : ViewModel() {
    private var _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState

    init {
        val dateTime = Calendar.getInstance()
        val month = dateTime.get(Calendar.MONTH)
        _homeState.update {
            it.copy(selectedMonth = month)
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