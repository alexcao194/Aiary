package com.alexcao.dexpense.core.modules

import com.alexcao.dexpense.data.data_sources.SystemDatabase
import com.alexcao.dexpense.data.repositories.ExpenseRepository
import com.alexcao.dexpense.data.repositories.ExpenseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

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