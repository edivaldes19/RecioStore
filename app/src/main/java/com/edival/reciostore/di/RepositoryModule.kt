package com.edival.reciostore.di

import com.edival.reciostore.data.dataSource.local.AddressLocalDataSource
import com.edival.reciostore.data.dataSource.local.ShoppingBagLocalDataSource
import com.edival.reciostore.data.dataSource.remote.AddressRemoteDataSource
import com.edival.reciostore.data.dataSource.remote.InfoRemoteDataSource
import com.edival.reciostore.data.dataSource.remote.OrdersRemoteDataSource
import com.edival.reciostore.data.repository.AddressRepositoryImpl
import com.edival.reciostore.data.repository.AuthRepositoryImpl
import com.edival.reciostore.data.repository.CategoriesRepositoryImpl
import com.edival.reciostore.data.repository.InfoRepositoryImpl
import com.edival.reciostore.data.repository.OrdersRepositoryImpl
import com.edival.reciostore.data.repository.ProductsRepositoryImpl
import com.edival.reciostore.data.repository.ShoppingBagRepositoryImpl
import com.edival.reciostore.data.repository.UsersRepositoryImpl
import com.edival.reciostore.domain.repository.AddressRepository
import com.edival.reciostore.domain.repository.AuthRepository
import com.edival.reciostore.domain.repository.CategoriesRepository
import com.edival.reciostore.domain.repository.InfoRepository
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
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun provideCategoriesRepository(impl: CategoriesRepositoryImpl): CategoriesRepository = impl

    @Provides
    fun provideProductsRepository(impl: ProductsRepositoryImpl): ProductsRepository = impl

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

    @Provides
    fun provideInfoRepository(infoRemoteDataSource: InfoRemoteDataSource): InfoRepository {
        return InfoRepositoryImpl(infoRemoteDataSource)
    }
}