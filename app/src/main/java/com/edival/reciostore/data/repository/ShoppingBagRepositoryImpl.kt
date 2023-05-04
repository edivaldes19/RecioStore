package com.edival.reciostore.data.repository

import android.util.Log
import com.edival.reciostore.data.dataSource.local.ShoppingBagLocalDataSource
import com.edival.reciostore.data.mapper.toShoppingBagEntity
import com.edival.reciostore.data.mapper.toShoppingBagProduct
import com.edival.reciostore.domain.model.ShoppingBagProduct
import com.edival.reciostore.domain.repository.ShoppingBagRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class ShoppingBagRepositoryImpl(private val localDS: ShoppingBagLocalDataSource) :
    ShoppingBagRepository {
    override fun getProductsBag(): Flow<List<ShoppingBagProduct>> = flow {
        localDS.getProductsBag().collect { shoppingBagProductEntities ->
            emit(shoppingBagProductEntities.map { entity -> entity.toShoppingBagProduct() })
            Log.d(
                "getProductsBag",
                "LOCAL: ${shoppingBagProductEntities.map { entity -> entity.toShoppingBagProduct() }}"
            )
        }
    }

    override suspend fun getProductBagById(id: String): ShoppingBagProduct? {
        runBlocking(Dispatchers.IO) { localDS.getProductBagById(id) }.also { productEntity ->
            productEntity?.let { entity -> return entity.toShoppingBagProduct() }
        }
        return null
    }

    override suspend fun addProductBag(product: ShoppingBagProduct) {
        localDS.getProductBagById(product.id).also { productEntity ->
            productEntity?.let { entity ->
                if (entity.id.isBlank()) localDS.insertProductBag(product.toShoppingBagEntity())
                else localDS.updateProductBag(product.id, product.quantity)
            }
        }
    }

    override suspend fun deleteProductBag(idProduct: String) {
        localDS.deleteProductBag(idProduct)
    }
}