package com.edival.reciostore.di

import com.edival.reciostore.data.repository.AuthRepositoryImpl
import com.edival.reciostore.data.repository.UsersRepositoryImpl
import com.edival.reciostore.data.repository.dataSource.AuthLocalDataSource
import com.edival.reciostore.data.repository.dataSource.AuthRemoteDataSource
import com.edival.reciostore.data.repository.dataSource.UsersRemoteDataSource
import com.edival.reciostore.domain.repository.AuthRepository
import com.edival.reciostore.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideAuthRepository(
        authRemoteDataSource: AuthRemoteDataSource, authLocalDataSource: AuthLocalDataSource
    ): AuthRepository {
        return AuthRepositoryImpl(authRemoteDataSource, authLocalDataSource)
    }

    @Provides
    fun provideUsersRepository(usersRemoteDataSource: UsersRemoteDataSource): UsersRepository {
        return UsersRepositoryImpl(usersRemoteDataSource)
    }
}