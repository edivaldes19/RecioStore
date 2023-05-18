package com.edival.reciostore.data.dataSource.remote

import com.edival.reciostore.domain.model.Product
import retrofit2.Response

interface ProductsRemoteDataSource {
    suspend fun getProducts(): Response<List<Product>>
    suspend fun getProductsByCategory(id: String): Response<List<Product>>
    suspend fun getProductsByName(name: String): Response<List<Product>>
    suspend fun createProduct(product: Product): Response<Product>
    suspend fun updateProduct(id: String, product: Product): Response<Product>
    suspend fun deleteProduct(id: String): Response<Unit>
}