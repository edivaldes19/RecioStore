package com.edival.reciostore.data.dataSource.local

import com.edival.reciostore.data.dataSource.local.dao.ProductsDAO
import com.edival.reciostore.data.dataSource.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

class ProductsLocalDataSourceImpl(private val productsDAO: ProductsDAO) : ProductsLocalDataSource {
    override fun getProducts(): Flow<List<ProductEntity>> = productsDAO.getProducts()
    override fun getProductsByCategory(id_category: String): Flow<List<ProductEntity>> {
        return productsDAO.getProductsByCategory(id_category)
    }

    override suspend fun insertProduct(product: ProductEntity) = productsDAO.insertProduct(product)
    override suspend fun insertAllProducts(products: List<ProductEntity>) {
        return productsDAO.insertAllProducts(products)
    }

    override suspend fun updateProduct(
        id: String, name: String, description: String, price: Double
    ) = productsDAO.updateProduct(id, name, description, price)

    override suspend fun deleteProduct(id: String) = productsDAO.deleteProduct(id)
}