package com.alexcao.aiary.core.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.alexcao.aiary.data.models.ExpenseInfo

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insertExpense(expense: ExpenseInfo)

    @Update
    suspend fun updateExpense(expense: ExpenseInfo)

    @Delete
    suspend fun deleteExpense(expense: ExpenseInfo)

    @Query("SELECT * FROM ExpenseInfo")
    suspend fun getAllExpenses(): List<ExpenseInfo>

    @Query("SELECT * FROM ExpenseInfo WHERE id = :id")
    suspend fun getExpenseById(id: Int): ExpenseInfo

    @Query("SELECT * FROM ExpenseInfo WHERE categoryId = :categoryId")
    suspend fun getExpensesByCategoryId(categoryId: Int): List<ExpenseInfo>

    @Query("SELECT * FROM ExpenseInfo WHERE expenseSourceId = :expenseSourceId")
    suspend fun getExpensesBySourceId(expenseSourceId: Int): List<ExpenseInfo>
    
    @Query("SELECT * FROM ExpenseInfo WHERE strftime('%m', date) = :month")
    suspend fun getExpensesByMonth(month: String): List<ExpenseInfo>
}