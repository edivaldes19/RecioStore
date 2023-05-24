package com.edival.reciostore.domain.repository

import android.content.Context
import android.net.Uri
import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getProducts(): Flow<Resource<List<Product>>>
    fun getProductsByCategory(id: String): Flow<Resource<List<Product>>>
    fun getProductsByName(name: String): Flow<Resource<List<Product>>>
    suspend fun createProduct(product: Product, images: List<Uri>): Resource<Product>
    suspend fun updateProduct(id: String, product: Product, images: List<Uri>?): Resource<Product>
    suspend fun deleteProduct(id: String): Resource<Unit>
    suspend fun downloadProdImages(
        ctx: Context, prodName: String, urls: List<String>
    ): Resource<Unit>
}