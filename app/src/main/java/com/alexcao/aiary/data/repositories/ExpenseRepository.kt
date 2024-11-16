package com.alexcao.aiary.data.repositories

import com.alexcao.aiary.data.data_sources.SystemDatabase
import com.alexcao.aiary.data.models.Expense
import javax.inject.Inject

interface ExpenseRepository {
    suspend fun getExpenses(): List<Expense>
    suspend fun addExpense(expense: Expense)
    suspend fun deleteExpense(expense: Expense)
}

class ExpenseRepositoryImpl @Inject constructor(
    private val systemDatabase: SystemDatabase
) : ExpenseRepository {
    override suspend fun getExpenses(): List<Expense> {
        return systemDatabase.expenseDao().getAllExpenses()
    }

    override suspend fun addExpense(expense: Expense) {
        systemDatabase.expenseDao().insertExpense(expense.info)
    }

    override suspend fun deleteExpense(expense: Expense) {
        systemDatabase.expenseDao().deleteExpense(expense.info)
    }
}