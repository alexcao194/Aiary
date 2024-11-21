package com.alexcao.dexpense.core.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.alexcao.dexpense.data.models.SourceInfo

@Dao
interface SourceDao {
    @Insert
    suspend fun insertSource(sourceInfo: SourceInfo)

    @Update
    suspend fun updateSource(sourceInfo: SourceInfo)

    @Delete
    suspend fun deleteSource(sourceInfo: SourceInfo)
}