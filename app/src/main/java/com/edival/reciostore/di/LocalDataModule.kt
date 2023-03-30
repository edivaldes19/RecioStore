package com.edival.reciostore.di

import com.edival.reciostore.data.dataSource.local.AuthLocalDataSource
import com.edival.reciostore.data.dataSource.local.AuthLocalDataSourceImpl
import com.edival.reciostore.data.dataSource.local.CategoriesLocalDataSource
import com.edival.reciostore.data.dataSource.local.CategoriesLocalDataSourceImpl
import com.edival.reciostore.data.dataSource.local.dao.CategoriesDAO
import com.edival.reciostore.data.dataSource.local.dataStore.AuthDataStore
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

    @Provides
    fun provideCategoriesLocalDataSource(categoriesDAO: CategoriesDAO): CategoriesLocalDataSource {
        return CategoriesLocalDataSourceImpl(categoriesDAO)
    }
}