package com.alexcao.dexpense.presentation.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexcao.dexpense.data.repositories.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val expenseRepository: ExpenseRepository
) : ViewModel() {
    private val _state: MutableStateFlow<SharedState> = MutableStateFlow(SharedState())
    val state: StateFlow<SharedState> = _state

    init {
        val dateTime = Calendar.getInstance()

        val month = dateTime.get(Calendar.MONTH)
        selectMonth(month)

        viewModelScope.launch {
            expenseRepository.getCategories().collect { categories ->
                _state.update {
                    it.copy(categories = categories)
                }
            }
        }

        viewModelScope.launch {
            expenseRepository.getSources().collect { sources ->
                _state.update {
                    it.copy(sources = sources)
                }
            }
        }
    }

    fun selectMonth(month: Int) {
        _state.update {
            it.copy(
                currentMonth = month
            )
        }
    }
}