package com.alexcao.aiary.data.repositories

import com.alexcao.aiary.core.Resource
import com.alexcao.aiary.data.data_sources.SystemDatabase
import com.alexcao.aiary.data.models.Expense
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.Month
import javax.inject.Inject

interface ExpenseRepository {
    suspend fun getExpenses(month: Int?): Flow<Resource<List<Expense>>>
    suspend fun addExpense(expense: Expense): Flow<Resource<Unit>>
    suspend fun deleteExpense(expense: Expense): Flow<Resource<Unit>>
}

class ExpenseRepositoryImpl @Inject constructor(
    private val systemDatabase: SystemDatabase
) : ExpenseRepository {
    override suspend fun getExpenses(month: Int?): Flow<Resource<List<Expense>>> = flow {
        emit(Resource.Loading())
        try {
            val expenses = if (month != null) {
                systemDatabase.expenseDao().getExpensesByMonth(month.toString())
            } else {
                systemDatabase.expenseDao().getAllExpenses()
            }
            emit(Resource.Success(expenses))
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred while fetching expenses"))
        }
    }

    override suspend fun addExpense(expense: Expense): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            systemDatabase.expenseDao().insertExpense(expense.info)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred while adding expense"))
        }
    }

    override suspend fun deleteExpense(expense: Expense): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            systemDatabase.expenseDao().deleteExpense(expense.info)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred while deleting expense"))
        }
    }
}