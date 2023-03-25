package com.edival.reciostore.di

import com.edival.reciostore.data.repository.dataSource.AuthRemoteDataSource
import com.edival.reciostore.data.repository.dataSource.UsersRemoteDataSource
import com.edival.reciostore.data.repository.dataSourceImpl.AuthRemoteDataSourceImpl
import com.edival.reciostore.data.repository.dataSourceImpl.UsersRemoteDataSourceImpl
import com.edival.reciostore.data.service.AuthService
import com.edival.reciostore.data.service.UsersService
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

    @Provides
    fun provideUsersRemoteDataSource(usersService: UsersService): UsersRemoteDataSource {
        return UsersRemoteDataSourceImpl(usersService)
    }
}