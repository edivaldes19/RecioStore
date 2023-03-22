package com.edival.reciostore.di

import com.edival.reciostore.data.dataStore.AuthDataStore
import com.edival.reciostore.data.repository.dataSource.AuthLocalDataSource
import com.edival.reciostore.data.repository.dataSourceImpl.AuthLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    fun provideAuthLocalDataSource(authDataStore: AuthDataStore): AuthLocalDataSource {
        return AuthLocalDataSourceImpl(authDataStore)
    }
}