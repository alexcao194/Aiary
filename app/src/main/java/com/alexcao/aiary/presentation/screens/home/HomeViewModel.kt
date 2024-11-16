package com.alexcao.aiary.presentation.screens.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState

    init {
        val dateTime = Calendar.getInstance()
        val month = dateTime.get(Calendar.MONTH)
        _homeState.update {
            it.copy(
                selectedMonth = month
            )
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