package com.edival.reciostore.data.repository

import com.edival.reciostore.data.dataSource.local.ShoppingBagLocalDataSource
import com.edival.reciostore.data.mapper.toShoppingBagEntity
import com.edival.reciostore.data.mapper.toShoppingBagProduct
import com.edival.reciostore.domain.model.ShoppingBagProduct
import com.edival.reciostore.domain.repository.ShoppingBagRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShoppingBagRepositoryImpl(private val localDS: ShoppingBagLocalDataSource) :
    ShoppingBagRepository {
    override fun getProductsBag(): Flow<List<ShoppingBagProduct>> = flow {
        localDS.getProductsBag().collect { shoppingBagProductEntities ->
            emit(shoppingBagProductEntities.map { entity -> entity.toShoppingBagProduct() })
        }
    }

    override suspend fun getProductBagById(id: String): ShoppingBagProduct? {
        localDS.getProductBagById(id).also { productEntity ->
            productEntity?.let { entity -> return entity.toShoppingBagProduct() }
        }
        return null
    }

    override suspend fun addProductBag(product: ShoppingBagProduct) {
        localDS.getProductBagById(product.id).also { productEntity ->
            if (productEntity == null) localDS.insertProductBag(product.toShoppingBagEntity())
            else localDS.updateProductBag(product.id, product.quantity)
        }
    }

    override suspend fun deleteProductBag(idProduct: String) {
        localDS.deleteProductBag(idProduct)
    }

    override suspend fun emptyShoppingBag() {
        localDS.emptyShoppingBag()
    }
}