package com.alexcao.dexpense.core.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.alexcao.dexpense.data.models.Source
import com.alexcao.dexpense.data.models.SourceAmount
import com.alexcao.dexpense.data.models.SourceInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface SourceDao {

    @Insert
    suspend fun insertSourceInfo(sourceInfo: SourceInfo): Long

    @Insert
    suspend fun insertSourceAmount(sourceAmount: SourceAmount): Long

    @Transaction
    suspend fun insertSource(source: Source) {
        val sourceInfoId = insertSourceInfo(source.info)
        val sourceAmount = source.amount.copy(id = sourceInfoId.toInt())
        insertSourceAmount(sourceAmount)
    }

    @Update
    suspend fun updateSourceInfo(sourceInfo: SourceInfo)

    @Update
    suspend fun updateSourceAmount(sourceAmount: SourceAmount)

    @Transaction
    suspend fun updateSource(source: Source) {
        updateSourceInfo(source.info)
        updateSourceAmount(source.amount)
    }

    @Delete
    suspend fun deleteSourceInfo(sourceInfo: SourceInfo)

    @Delete
    suspend fun deleteSourceAmount(sourceAmount: SourceAmount)

    @Transaction
    suspend fun deleteSource(source: Source) {
        deleteSourceAmount(source.amount)
        deleteSourceInfo(source.info)
    }

    @Transaction
    @Query("SELECT * FROM SourceInfo")
    fun getAllSources(): Flow<List<Source>>

    @Transaction
    @Query("SELECT * FROM SourceInfo WHERE id = :id")
    suspend fun getSourceById(id: Int): Source
}
