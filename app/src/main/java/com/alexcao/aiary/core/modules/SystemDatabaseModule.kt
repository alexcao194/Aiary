package com.alexcao.aiary.core.modules

import android.content.Context
import androidx.room.Room
import com.alexcao.aiary.data.data_sources.SystemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SystemDatabaseModule {

    @Provides
    @Singleton
    fun provideSystemDatabase(@ApplicationContext context: Context): SystemDatabase {
        return Room.databaseBuilder(
            context,
            SystemDatabase::class.java,
            SystemDatabase.DATABASE_NAME
        ).build()
    }

}