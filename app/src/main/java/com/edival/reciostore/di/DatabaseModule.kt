package com.edival.reciostore.di

import android.app.Application
import androidx.room.Room
import com.edival.reciostore.data.dataSource.local.dao.CategoriesDAO
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
        return Room.databaseBuilder(app, AppDB::class.java, "recio_store_db")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideCategoriesDAO(db: AppDB): CategoriesDAO = db.categoriesDao()
}