package com.edival.reciostore.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edival.reciostore.data.dataSource.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDAO {
    @Query("SELECT * FROM products")
    fun getProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE id_category = :id_category ")
    fun getProductsByCategory(id_category: String): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(products: List<ProductEntity>)

    @Query("UPDATE products SET name = :name, description = :description, price = :price, img1 = :img1, img2 = :img2 WHERE id = :id")
    suspend fun updateProduct(
        id: String, name: String, description: String, price: Double, img1: String, img2: String
    )

    @Query("DELETE FROM products WHERE id = :id")
    suspend fun deleteProduct(id: String)
}