package com.edival.reciostore.di

import com.edival.reciostore.data.dataSource.remote.*
import com.edival.reciostore.data.dataSource.remote.service.AuthService
import com.edival.reciostore.data.dataSource.remote.service.CategoriesService
import com.edival.reciostore.data.dataSource.remote.service.UsersService
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

    @Provides
    fun provideCategoriesRemoteDataSource(categoriesService: CategoriesService): CategoriesRemoteDataSource {
        return CategoriesRemoteDataSourceImpl(categoriesService)
    }
}