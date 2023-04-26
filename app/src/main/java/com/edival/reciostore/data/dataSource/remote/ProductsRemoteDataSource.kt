package com.edival.reciostore.data.dataSource.remote

import com.edival.reciostore.domain.model.Product
import retrofit2.Response
import java.io.File

interface ProductsRemoteDataSource {
    suspend fun getProducts(): Response<List<Product>>
    suspend fun getProductsByCategory(id: String): Response<List<Product>>
    suspend fun createProduct(files: List<File>, product: Product): Response<Product>
    suspend fun updateProduct(id: String, product: Product): Response<Product>
    suspend fun updateProductImages(
        files: List<File>, id: String, product: Product
    ): Response<Product>

    suspend fun deleteProduct(id: String): Response<Unit>
}