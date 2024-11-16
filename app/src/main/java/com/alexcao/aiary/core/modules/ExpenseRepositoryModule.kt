package com.alexcao.aiary.core.modules

import com.alexcao.aiary.data.data_sources.SystemDatabase
import com.alexcao.aiary.data.repositories.ExpenseRepository
import com.alexcao.aiary.data.repositories.ExpenseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class ExpenseRepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideExpenseRepository(systemDatabase: SystemDatabase): ExpenseRepository {
        return ExpenseRepositoryImpl(
            systemDatabase = systemDatabase
        )
    }
}