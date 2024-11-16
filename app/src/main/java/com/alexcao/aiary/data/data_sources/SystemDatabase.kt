package com.alexcao.aiary.data.data_sources

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alexcao.aiary.core.database.daos.ExpenseDao
import com.alexcao.aiary.core.database.Converters
import com.alexcao.aiary.data.models.ExpenseCategory
import com.alexcao.aiary.data.models.ExpenseInfo
import com.alexcao.aiary.data.models.ExpenseSource

@Database(
    entities = [ExpenseInfo::class, ExpenseCategory::class, ExpenseSource::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class SystemDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

    companion object {
        const val DATABASE_NAME = "system_database"
    }
}