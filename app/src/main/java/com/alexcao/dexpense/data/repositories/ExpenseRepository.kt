package com.alexcao.dexpense.data.repositories

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.alexcao.dexpense.core.Resource
import com.alexcao.dexpense.data.data_sources.SystemDatabase
import com.alexcao.dexpense.data.models.Expense
import com.alexcao.dexpense.data.models.Category
import com.alexcao.dexpense.data.models.Source
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ExpenseRepository {
    fun getExpenses(month: Int?): Flow<List<Expense>>
    fun getCategories(): Flow<List<Category>>
    fun getSources(): Flow<List<Source>>
    suspend fun addExpense(expense: Expense): Flow<Resource<Unit>>
    suspend fun updateExpense(expense: Expense): Flow<Resource<Unit>>
    suspend fun deleteExpense(expense: Expense): Flow<Resource<Unit>>
    suspend fun addCategory(category: Category): Flow<Resource<Unit>>
    suspend fun updateCategory(category: Category): Flow<Resource<Unit>>
    suspend fun deleteCategory(category: Category): Flow<Resource<Unit>>
    suspend fun addSource(source: Source): Flow<Resource<Unit>>
    suspend fun updateSource(source: Source): Flow<Resource<Unit>>
    suspend fun deleteSource(source: Source): Flow<Resource<Unit>>
}

class ExpenseRepositoryImpl @Inject constructor(
    private val systemDatabase: SystemDatabase
) : ExpenseRepository {
    override fun getExpenses(month: Int?): Flow<List<Expense>> =
        systemDatabase
            .expenseDao()
            .getExpensesByMonth(month.toString())

    override fun getCategories(): Flow<List<Category>> {
        return systemDatabase.categoryDao().getAllCategories()
    }

    override fun getSources(): Flow<List<Source>> {
        return systemDatabase.sourceDao().getAllSources()
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

    override suspend fun addCategory(category: Category): Flow<Resource<Unit>> =
        flow {
            emit(Resource.Loading())
            try {
                systemDatabase.categoryDao().insertCategory(category)
                emit(Resource.Success(Unit))
            } catch (e: SQLiteConstraintException) {
                emit(Resource.Error("A category with the same name already exists"))
            } catch (e: Exception) {
                emit(Resource.Error("An error occurred while adding category"))
            }
        }

    override suspend fun updateCategory(category: Category): Flow<Resource<Unit>> =
        flow {
            emit(Resource.Loading())
            try {
                systemDatabase.categoryDao().updateCategory(category)
                emit(Resource.Success(Unit))
            } catch (e: SQLiteConstraintException) {
                emit(Resource.Error("A category with the same name already exists"))
            } catch (e: Exception) {
                emit(Resource.Error("An error occurred while updating category"))
            }
        }

    override suspend fun deleteCategory(category: Category): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            systemDatabase.categoryDao().deleteCategory(category)
            emit(Resource.Success(Unit))
        } catch (e: SQLiteConstraintException) {
            emit(Resource.Error("An expense that uses this category exists"))
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred while deleting category"))
        }
    }

    override suspend fun addSource(source: Source): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            systemDatabase.sourceDao().insertSource(source)
            emit(Resource.Success(Unit))
        } catch (e: SQLiteConstraintException) {
            emit(Resource.Error("A category with the same name already exists"))
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred while adding source"))
        }
    }

    override suspend fun updateSource(source: Source): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            systemDatabase.sourceDao().updateSource(source)
            emit(Resource.Success(Unit))
        } catch (e: SQLiteConstraintException) {
            emit(Resource.Error("A category with the same name already exists"))
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred while updating source"))
        }
    }

    override suspend fun deleteSource(source: Source): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            systemDatabase.sourceDao().deleteSource(source)
            emit(Resource.Success(Unit))
        } catch (e: SQLiteConstraintException) {
            emit(Resource.Error("A expense that uses this source exists"))
        } catch (e: Exception) {
            Log.d("TAG", "deleteSource: $e")
            emit(Resource.Error("An error occurred while deleting source"))
        }
    }
}