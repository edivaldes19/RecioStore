package com.edival.reciostore.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edival.reciostore.data.dataSource.local.entity.ShoppingBagProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingBagDAO {
    @Query("SELECT * FROM shopping_bag")
    fun getProductsBag(): Flow<List<ShoppingBagProductEntity>>

    @Query("SELECT * FROM shopping_bag WHERE id = :id")
    suspend fun getProductBagById(id: String): ShoppingBagProductEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductBag(product: ShoppingBagProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProductsBag(products: List<ShoppingBagProductEntity>)

    @Query("UPDATE shopping_bag SET quantity = :quantity WHERE id = :id")
    suspend fun updateProductBag(id: String, quantity: Int)

    @Query("DELETE FROM shopping_bag WHERE id = :id")
    suspend fun deleteProductBag(id: String)

    @Query("DELETE FROM shopping_bag")
    suspend fun emptyShoppingBag()
}