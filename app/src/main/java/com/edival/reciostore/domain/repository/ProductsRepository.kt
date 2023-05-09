package com.edival.reciostore.domain.repository

import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

interface ProductsRepository {
    fun getProducts(): Flow<Resource<List<Product>>>
    fun getProductsByCategory(id: String): Flow<Resource<List<Product>>>
    fun getProductsByName(name: String): Flow<Resource<List<Product>>>
    suspend fun createProduct(files: List<File>, product: Product): Resource<Product>
    suspend fun updateProduct(id: String, product: Product): Resource<Product>
    suspend fun updateProductImages(
        files: List<File>, id: String, product: Product
    ): Resource<Product>

    suspend fun deleteProduct(id: String): Resource<Unit>
}