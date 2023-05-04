package com.edival.reciostore.di

import com.edival.reciostore.data.dataSource.local.AddressLocalDataSource
import com.edival.reciostore.data.dataSource.local.AddressLocalDataSourceImpl
import com.edival.reciostore.data.dataSource.local.AuthLocalDataSource
import com.edival.reciostore.data.dataSource.local.AuthLocalDataSourceImpl
import com.edival.reciostore.data.dataSource.local.CategoriesLocalDataSource
import com.edival.reciostore.data.dataSource.local.CategoriesLocalDataSourceImpl
import com.edival.reciostore.data.dataSource.local.ProductsLocalDataSource
import com.edival.reciostore.data.dataSource.local.ProductsLocalDataSourceImpl
import com.edival.reciostore.data.dataSource.local.ShoppingBagLocalDataSource
import com.edival.reciostore.data.dataSource.local.ShoppingBagLocalDataSourceImpl
import com.edival.reciostore.data.dataSource.local.dao.AddressDAO
import com.edival.reciostore.data.dataSource.local.dao.CategoriesDAO
import com.edival.reciostore.data.dataSource.local.dao.ProductsDAO
import com.edival.reciostore.data.dataSource.local.dao.ShoppingBagDAO
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

    @Provides
    fun provideProductsLocalDataSource(productsDAO: ProductsDAO): ProductsLocalDataSource {
        return ProductsLocalDataSourceImpl(productsDAO)
    }

    @Provides
    fun provideShoppingBagLocalDataSource(shoppingBagDAO: ShoppingBagDAO): ShoppingBagLocalDataSource {
        return ShoppingBagLocalDataSourceImpl(shoppingBagDAO)
    }

    @Provides
    fun provideAddressLocalDataSource(addressDAO: AddressDAO): AddressLocalDataSource {
        return AddressLocalDataSourceImpl(addressDAO)
    }
}