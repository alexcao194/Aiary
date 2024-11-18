package com.alexcao.aiary.data.data_sources

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alexcao.aiary.core.database.converters.BigDecimalConverter
import com.alexcao.aiary.core.database.converters.ColorConverter
import com.alexcao.aiary.core.database.daos.ExpenseDao
import com.alexcao.aiary.core.database.converters.LocalDateConverter
import com.alexcao.aiary.data.models.ExpenseCategory
import com.alexcao.aiary.data.models.ExpenseInfo
import com.alexcao.aiary.data.models.ExpenseSource

@Database(
    entities = [ExpenseInfo::class, ExpenseCategory::class, ExpenseSource::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(LocalDateConverter::class, ColorConverter::class, BigDecimalConverter::class)
abstract class SystemDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

    companion object {
        const val DATABASE_NAME = "system_database"
    }
}