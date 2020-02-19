package com.buggyarts.hubtrends.di.module

import com.buggyarts.hubtrends.di.ViewModelScope
import com.buggyarts.hubtrends.repo.Repository
import com.buggyarts.hubtrends.repo.RepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @ViewModelScope
    fun providesRepository(repository: RepositoryImpl): Repository {
        return repository
    }
}