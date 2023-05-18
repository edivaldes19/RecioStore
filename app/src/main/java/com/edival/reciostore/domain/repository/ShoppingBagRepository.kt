package com.edival.reciostore.domain.repository

import com.edival.reciostore.domain.model.ShoppingBagProduct
import kotlinx.coroutines.flow.Flow

interface ShoppingBagRepository {
    fun getProductsBag(): Flow<List<ShoppingBagProduct>>
    suspend fun getProductBagById(id: String): ShoppingBagProduct?
    suspend fun addProductBag(product: ShoppingBagProduct)
    suspend fun deleteProductBag(idProduct: String)
    suspend fun emptyShoppingBag()
}