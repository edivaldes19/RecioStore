package com.edival.reciostore.di

import com.edival.reciostore.data.dataSource.remote.*
import com.edival.reciostore.data.dataSource.remote.service.AddressService
import com.edival.reciostore.data.dataSource.remote.service.AuthService
import com.edival.reciostore.data.dataSource.remote.service.CategoriesService
import com.edival.reciostore.data.dataSource.remote.service.OrdersService
import com.edival.reciostore.data.dataSource.remote.service.ProductsService
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

    @Provides
    fun provideProductsRemoteDataSource(productsService: ProductsService): ProductsRemoteDataSource {
        return ProductsRemoteDataSourceImpl(productsService)
    }

    @Provides
    fun provideAddressRemoteDataSource(addressService: AddressService): AddressRemoteDataSource {
        return AddressRemoteDataSourceImpl(addressService)
    }

    @Provides
    fun provideOrdersRemoteDataSource(ordersService: OrdersService): OrdersRemoteDataSource {
        return OrdersRemoteDataSourceImpl(ordersService)
    }
}