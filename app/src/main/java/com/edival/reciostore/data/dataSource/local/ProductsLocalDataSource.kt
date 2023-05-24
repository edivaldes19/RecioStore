package com.edival.reciostore.data.dataSource.local

import com.edival.reciostore.data.dataSource.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductsLocalDataSource {
    fun getProducts(): Flow<List<ProductEntity>>
    fun getProductsByCategory(id_category: String): Flow<List<ProductEntity>>
    suspend fun insertProduct(product: ProductEntity)
    suspend fun insertAllProducts(products: List<ProductEntity>)
    suspend fun updateProduct(
        id: String, name: String, description: String, price: Double, phi: String
    )

    suspend fun deleteProduct(id: String)
}