package com.edival.reciostore.di

import com.edival.reciostore.core.Config
import com.edival.reciostore.data.dataSource.local.dataStore.AuthDataStore
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(dataStore: AuthDataStore): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val token = runBlocking { dataStore.getUser().first().token }
            val newRequest =
                chain.request().newBuilder().addHeader("Authorization", token.orEmpty()).build()
            chain.proceed(newRequest)
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Config.BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideUsersService(retrofit: Retrofit): UsersService {
        return retrofit.create(UsersService::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoriesService(retrofit: Retrofit): CategoriesService {
        return retrofit.create(CategoriesService::class.java)
    }

    @Provides
    @Singleton
    fun provideProductsService(retrofit: Retrofit): ProductsService {
        return retrofit.create(ProductsService::class.java)
    }

    @Provides
    @Singleton
    fun provideAddressService(retrofit: Retrofit): AddressService {
        return retrofit.create(AddressService::class.java)
    }

    @Provides
    @Singleton
    fun provideOrdersService(retrofit: Retrofit): OrdersService {
        return retrofit.create(OrdersService::class.java)
    }
}