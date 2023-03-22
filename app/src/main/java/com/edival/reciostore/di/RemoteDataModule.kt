package com.edival.reciostore.di

import com.edival.reciostore.data.repository.dataSource.AuthRemoteDataSource
import com.edival.reciostore.data.repository.dataSourceImpl.AuthRemoteDataSourceImpl
import com.edival.reciostore.data.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideAuthRemoteDataSource(authService: AuthService): AuthRemoteDataSource {
        return AuthRemoteDataSourceImpl(authService)
    }
}