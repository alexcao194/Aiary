package com.alexcao.dexpense.core.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.alexcao.dexpense.data.models.Expense
import com.alexcao.dexpense.data.models.ExpenseCategory
import com.alexcao.dexpense.data.models.ExpenseInfo
import com.alexcao.dexpense.data.models.ExpenseSource
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insertExpense(expense: ExpenseInfo)

    @Update
    suspend fun updateExpense(expense: ExpenseInfo)

    @Delete
    suspend fun deleteExpense(expense: ExpenseInfo)

    @Insert
    suspend fun insertCategory(category: ExpenseCategory)

    @Update
    suspend fun updateCategory(category: ExpenseCategory)

    @Delete
    suspend fun deleteCategory(category: ExpenseCategory)

    @Insert
    suspend fun insertSource(expenseSource: ExpenseSource)

    @Update
    suspend fun updateSource(expenseSource: ExpenseSource)

    @Delete
    suspend fun deleteSource(expenseSource: ExpenseSource)

    @Transaction
    @Query("SELECT * FROM ExpenseInfo")
    suspend fun getAllExpenses(): List<Expense>

    @Transaction
    @Query("SELECT * FROM ExpenseInfo WHERE id = :id")
    suspend fun getExpenseById(id: Int): Expense

    @Transaction
    @Query("SELECT * FROM ExpenseInfo WHERE categoryId = :categoryId")
    fun getExpensesByCategoryId(categoryId: Int): Flow<List<Expense>>

    @Transaction
    @Query("SELECT * FROM ExpenseInfo WHERE expenseSourceId = :expenseSourceId")
    fun getExpensesBySourceId(expenseSourceId: Int): Flow<List<Expense>>

    @Transaction
    @Query("SELECT * FROM ExpenseInfo WHERE strftime('%m', date) = :month")
    fun getExpensesByMonth(month: String): Flow<List<Expense>>

    @Query("SELECT * FROM ExpenseCategory")
    fun getAllCategories(): Flow<List<ExpenseCategory>>

    @Query("SELECT * FROM ExpenseSource")
    fun getAllSources(): Flow<List<ExpenseSource>>
}