package com.edival.reciostore.data.dataSource.local

import com.edival.reciostore.data.dataSource.local.entity.ShoppingBagProductEntity
import kotlinx.coroutines.flow.Flow

interface ShoppingBagLocalDataSource {
    fun getProductsBag(): Flow<List<ShoppingBagProductEntity>>
    suspend fun getProductBagById(id: String): ShoppingBagProductEntity?
    suspend fun insertProductBag(product: ShoppingBagProductEntity)
    suspend fun insertAllProductsBag(products: List<ShoppingBagProductEntity>)
    suspend fun updateProductBag(id: String, quantity: Int)
    suspend fun deleteProductBag(id: String)
    suspend fun emptyShoppingBag()
}