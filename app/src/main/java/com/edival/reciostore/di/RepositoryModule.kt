package com.edival.reciostore.di

import com.edival.reciostore.data.dataSource.local.AuthLocalDataSource
import com.edival.reciostore.data.dataSource.local.CategoriesLocalDataSource
import com.edival.reciostore.data.dataSource.local.ProductsLocalDataSource
import com.edival.reciostore.data.dataSource.remote.AuthRemoteDataSource
import com.edival.reciostore.data.dataSource.remote.CategoriesRemoteDataSource
import com.edival.reciostore.data.dataSource.remote.ProductsRemoteDataSource
import com.edival.reciostore.data.dataSource.remote.UsersRemoteDataSource
import com.edival.reciostore.data.repository.AuthRepositoryImpl
import com.edival.reciostore.data.repository.CategoriesRepositoryImpl
import com.edival.reciostore.data.repository.ProductsRepositoryImpl
import com.edival.reciostore.data.repository.UsersRepositoryImpl
import com.edival.reciostore.domain.repository.AuthRepository
import com.edival.reciostore.domain.repository.CategoriesRepository
import com.edival.reciostore.domain.repository.ProductsRepository
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
    ): AuthRepository = AuthRepositoryImpl(authRemoteDataSource, authLocalDataSource)

    @Provides
    fun provideUsersRepository(usersRemoteDataSource: UsersRemoteDataSource): UsersRepository {
        return UsersRepositoryImpl(usersRemoteDataSource)
    }

    @Provides
    fun provideCategoriesRepository(
        categoriesLocalDataSource: CategoriesLocalDataSource,
        categoriesRemoteDataSource: CategoriesRemoteDataSource
    ): CategoriesRepository {
        return CategoriesRepositoryImpl(categoriesLocalDataSource, categoriesRemoteDataSource)
    }

    @Provides
    fun provideProductsRepository(
        productsLocalDataSource: ProductsLocalDataSource,
        productsRemoteDataSource: ProductsRemoteDataSource
    ): ProductsRepository {
        return ProductsRepositoryImpl(productsLocalDataSource, productsRemoteDataSource)
    }
}