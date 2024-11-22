package com.alexcao.dexpense.data.data_sources

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alexcao.dexpense.core.database.converters.BigDecimalConverter
import com.alexcao.dexpense.core.database.converters.ColorConverter
import com.alexcao.dexpense.core.database.daos.ExpenseDao
import com.alexcao.dexpense.core.database.converters.LocalDateConverter
import com.alexcao.dexpense.core.database.daos.CategoryDao
import com.alexcao.dexpense.core.database.daos.SourceDao
import com.alexcao.dexpense.data.models.Category
import com.alexcao.dexpense.data.models.ExpenseInfo
import com.alexcao.dexpense.data.models.SourceAmount
import com.alexcao.dexpense.data.models.SourceInfo

@Database(
    entities = [ExpenseInfo::class, Category::class, SourceInfo::class, SourceAmount::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(LocalDateConverter::class, ColorConverter::class, BigDecimalConverter::class)
abstract class SystemDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun categoryDao(): CategoryDao
    abstract fun sourceDao(): SourceDao

    companion object {
        const val DATABASE_NAME = "system_database"
    }
}