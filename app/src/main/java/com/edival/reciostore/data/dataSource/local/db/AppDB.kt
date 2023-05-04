package com.edival.reciostore.data.dataSource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.edival.reciostore.data.dataSource.local.dao.AddressDAO
import com.edival.reciostore.data.dataSource.local.dao.CategoriesDAO
import com.edival.reciostore.data.dataSource.local.dao.ProductsDAO
import com.edival.reciostore.data.dataSource.local.dao.ShoppingBagDAO
import com.edival.reciostore.data.dataSource.local.entity.AddressEntity
import com.edival.reciostore.data.dataSource.local.entity.CategoryEntity
import com.edival.reciostore.data.dataSource.local.entity.ProductEntity
import com.edival.reciostore.data.dataSource.local.entity.ShoppingBagProductEntity

@Database(
    entities = [CategoryEntity::class, ProductEntity::class, ShoppingBagProductEntity::class, AddressEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDB : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDAO
    abstract fun productsDao(): ProductsDAO
    abstract fun shoppingBagDAO(): ShoppingBagDAO
    abstract fun addressDAO(): AddressDAO
}