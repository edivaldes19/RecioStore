package com.edival.reciostore.di

import com.edival.reciostore.core.Config
import com.edival.reciostore.data.dataSource.remote.*
import com.edival.reciostore.data.dataSource.remote.service.AddressService
import com.edival.reciostore.data.dataSource.remote.service.AuthService
import com.edival.reciostore.data.dataSource.remote.service.CategoriesService
import com.edival.reciostore.data.dataSource.remote.service.InfoService
import com.edival.reciostore.data.dataSource.remote.service.OrdersService
import com.edival.reciostore.data.dataSource.remote.service.ProductsService
import com.edival.reciostore.data.dataSource.remote.service.UsersService
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

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

    @Provides
    fun provideInfoRemoteDataSource(infoService: InfoService): InfoRemoteDataSource {
        return InfoRemoteDataSourceImpl(infoService)
    }

    @Provides
    fun provideFirebaseMessaging(): FirebaseMessaging = Firebase.messaging

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = Firebase.storage

    @Provides
    @Named(Config.CATEGORIES_URL)
    fun provideStorageCategoriesRef(storage: FirebaseStorage): StorageReference {
        return storage.reference.child(Config.CATEGORIES_URL)
    }

    @Provides
    @Named(Config.PRODUCTS_URL)
    fun provideStorageProductsRef(storage: FirebaseStorage): StorageReference {
        return storage.reference.child(Config.PRODUCTS_URL)
    }

    @Provides
    @Named(Config.USERS_URL)
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference {
        return storage.reference.child(Config.USERS_URL)
    }
}