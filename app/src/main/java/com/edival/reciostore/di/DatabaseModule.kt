package com.edival.reciostore.di

import android.app.Application
import androidx.room.Room
import com.edival.reciostore.core.Config
import com.edival.reciostore.data.dataSource.local.dao.AddressDAO
import com.edival.reciostore.data.dataSource.local.dao.CategoriesDAO
import com.edival.reciostore.data.dataSource.local.dao.ProductsDAO
import com.edival.reciostore.data.dataSource.local.dao.ShoppingBagDAO
import com.edival.reciostore.data.dataSource.local.db.AppDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDB {
        return Room.databaseBuilder(app, AppDB::class.java, Config.DB_NAME)
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideCategoriesDAO(db: AppDB): CategoriesDAO = db.categoriesDao()

    @Provides
    @Singleton
    fun provideProductsDAO(db: AppDB): ProductsDAO = db.productsDao()

    @Provides
    @Singleton
    fun provideShoppingBagDAO(db: AppDB): ShoppingBagDAO = db.shoppingBagDAO()

    @Provides
    @Singleton
    fun provideAddressDAO(db: AppDB): AddressDAO = db.addressDAO()
}