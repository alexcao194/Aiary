package com.alexcao.aiary.data.repositories

import android.database.sqlite.SQLiteConstraintException
import com.alexcao.aiary.core.Resource
import com.alexcao.aiary.data.data_sources.SystemDatabase
import com.alexcao.aiary.data.models.Expense
import com.alexcao.aiary.data.models.ExpenseCategory
import com.alexcao.aiary.data.models.ExpenseSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ExpenseRepository {
    fun getExpenses(month: Int?): Flow<List<Expense>>
    fun getExpensesCategory(): Flow<List<ExpenseCategory>>
    fun getExpensesSource(): Flow<List<ExpenseSource>>
    suspend fun addExpense(expense: Expense): Flow<Resource<Unit>>
    suspend fun updateExpense(expense: Expense): Flow<Resource<Unit>>
    suspend fun deleteExpense(expense: Expense): Flow<Resource<Unit>>
    suspend fun addCategory(expenseCategory: ExpenseCategory): Flow<Resource<Unit>>
    suspend fun updateCategory(expenseCategory: ExpenseCategory): Flow<Resource<Unit>>
    suspend fun deleteCategory(category: ExpenseCategory): Flow<Resource<Unit>>
    suspend fun addSource(source: ExpenseSource): Flow<Resource<Unit>>
    suspend fun updateSource(source: ExpenseSource): Flow<Resource<Unit>>
    suspend fun deleteSource(source: ExpenseSource): Flow<Resource<Unit>>
}

class ExpenseRepositoryImpl @Inject constructor(
    private val systemDatabase: SystemDatabase
) : ExpenseRepository {
    override fun getExpenses(month: Int?): Flow<List<Expense>> =
        systemDatabase
            .expenseDao()
            .getExpensesByMonth(month.toString())

    override fun getExpensesCategory(): Flow<List<ExpenseCategory>> {
        return systemDatabase.expenseDao().getAllCategories()
    }

    override fun getExpensesSource(): Flow<List<ExpenseSource>> {
        return systemDatabase.expenseDao().getAllSources()
    }

    override suspend fun addExpense(expense: Expense): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            systemDatabase.expenseDao().insertExpense(expense.info)
            emit(Resource.Success(Unit))
        } catch (e: SQLiteConstraintException) {
            emit(Resource.Error("A category with the same name already exists"))
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred while adding expense"))
        }
    }

    override suspend fun updateExpense(expense: Expense): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            systemDatabase.expenseDao().updateExpense(expense.info)
            emit(Resource.Success(Unit))
        } catch (e: SQLiteConstraintException) {
            emit(Resource.Error("A category with the same name already exists"))
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred while updating expense"))
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

    override suspend fun addCategory(expenseCategory: ExpenseCategory): Flow<Resource<Unit>> =
        flow {
            emit(Resource.Loading())
            try {
                systemDatabase.expenseDao().insertCategory(expenseCategory)
                emit(Resource.Success(Unit))
            } catch (e: SQLiteConstraintException) {
                emit(Resource.Error("A category with the same name already exists"))
            } catch (e: Exception) {
                emit(Resource.Error("An error occurred while adding category"))
            }
        }

    override suspend fun updateCategory(expenseCategory: ExpenseCategory): Flow<Resource<Unit>> =
        flow {
            emit(Resource.Loading())
            try {
                systemDatabase.expenseDao().updateCategory(expenseCategory)
                emit(Resource.Success(Unit))
            } catch (e: SQLiteConstraintException) {
                emit(Resource.Error("A category with the same name already exists"))
            } catch (e: Exception) {
                emit(Resource.Error("An error occurred while updating category"))
            }
        }

    override suspend fun deleteCategory(category: ExpenseCategory): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            systemDatabase.expenseDao().deleteCategory(category)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred while deleting category"))
        }
    }

    override suspend fun addSource(source: ExpenseSource): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            systemDatabase.expenseDao().insertSource(source)
            emit(Resource.Success(Unit))
        } catch (e: SQLiteConstraintException) {
            emit(Resource.Error("A category with the same name already exists"))
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred while adding source"))
        }
    }

    override suspend fun updateSource(source: ExpenseSource): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            systemDatabase.expenseDao().updateSource(source)
            emit(Resource.Success(Unit))
        } catch (e: SQLiteConstraintException) {
            emit(Resource.Error("A category with the same name already exists"))
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred while updating source"))
        }
    }

    override suspend fun deleteSource(source: ExpenseSource): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            systemDatabase.expenseDao().deleteSource(source)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred while deleting source"))
        }
    }
}