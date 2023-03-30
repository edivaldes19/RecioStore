package com.edival.reciostore.data.dataSource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.edival.reciostore.data.dataSource.local.dao.CategoriesDAO
import com.edival.reciostore.data.dataSource.local.entity.CategoryEntity

@Database(entities = [CategoryEntity::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDAO
}