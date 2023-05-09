package com.edival.reciostore.di

import com.edival.reciostore.data.dataSource.local.AddressLocalDataSource
import com.edival.reciostore.data.dataSource.local.AuthLocalDataSource
import com.edival.reciostore.data.dataSource.local.CategoriesLocalDataSource
import com.edival.reciostore.data.dataSource.local.ProductsLocalDataSource
import com.edival.reciostore.data.dataSource.local.ShoppingBagLocalDataSource
import com.edival.reciostore.data.dataSource.remote.AddressRemoteDataSource
import com.edival.reciostore.data.dataSource.remote.AuthRemoteDataSource
import com.edival.reciostore.data.dataSource.remote.CategoriesRemoteDataSource
import com.edival.reciostore.data.dataSource.remote.OrdersRemoteDataSource
import com.edival.reciostore.data.dataSource.remote.ProductsRemoteDataSource
import com.edival.reciostore.data.dataSource.remote.UsersRemoteDataSource
import com.edival.reciostore.data.repository.AddressRepositoryImpl
import com.edival.reciostore.data.repository.AuthRepositoryImpl
import com.edival.reciostore.data.repository.CategoriesRepositoryImpl
import com.edival.reciostore.data.repository.OrdersRepositoryImpl
import com.edival.reciostore.data.repository.ProductsRepositoryImpl
import com.edival.reciostore.data.repository.ShoppingBagRepositoryImpl
import com.edival.reciostore.data.repository.UsersRepositoryImpl
import com.edival.reciostore.domain.repository.AddressRepository
import com.edival.reciostore.domain.repository.AuthRepository
import com.edival.reciostore.domain.repository.CategoriesRepository
import com.edival.reciostore.domain.repository.OrdersRepository
import com.edival.reciostore.domain.repository.ProductsRepository
import com.edival.reciostore.domain.repository.ShoppingBagRepository
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
        authLocalDataSource: AuthLocalDataSource, authRemoteDataSource: AuthRemoteDataSource
    ): AuthRepository = AuthRepositoryImpl(authLocalDataSource, authRemoteDataSource)

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

    @Provides
    fun provideShoppingBagRepository(shoppingBagLocalDataSource: ShoppingBagLocalDataSource): ShoppingBagRepository {
        return ShoppingBagRepositoryImpl(shoppingBagLocalDataSource)
    }

    @Provides
    fun provideAddressRepository(
        addressLocalDataSource: AddressLocalDataSource,
        addressRemoteDataSource: AddressRemoteDataSource
    ): AddressRepository {
        return AddressRepositoryImpl(addressLocalDataSource, addressRemoteDataSource)
    }

    @Provides
    fun provideOrdersRepository(ordersRemoteDataSource: OrdersRemoteDataSource): OrdersRepository {
        return OrdersRepositoryImpl(ordersRemoteDataSource)
    }
}