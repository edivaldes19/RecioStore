package com.edival.reciostore.data.dataSource.local

import com.edival.reciostore.data.dataSource.local.dao.ShoppingBagDAO
import com.edival.reciostore.data.dataSource.local.entity.ShoppingBagProductEntity
import kotlinx.coroutines.flow.Flow

class ShoppingBagLocalDataSourceImpl(private val shoppingBagDAO: ShoppingBagDAO) :
    ShoppingBagLocalDataSource {
    override fun getProductsBag(): Flow<List<ShoppingBagProductEntity>> {
        return shoppingBagDAO.getProductsBag()
    }

    override suspend fun getProductBagById(id: String): ShoppingBagProductEntity? {
        return shoppingBagDAO.getProductBagById(id)
    }

    override suspend fun insertProductBag(product: ShoppingBagProductEntity) {
        return shoppingBagDAO.insertProductBag(product)
    }

    override suspend fun insertAllProductsBag(products: List<ShoppingBagProductEntity>) {
        return shoppingBagDAO.insertAllProductsBag(products)
    }

    override suspend fun updateProductBag(id: String, quantity: Int) {
        return shoppingBagDAO.updateProductBag(id, quantity)
    }

    override suspend fun deleteProductBag(id: String) {
        return shoppingBagDAO.deleteProductBag(id)
    }

    override suspend fun emptyShoppingBag() = shoppingBagDAO.emptyShoppingBag()
}