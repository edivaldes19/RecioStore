package com.edival.reciostore.data.dataSource.remote.service

import com.edival.reciostore.core.Config
import com.edival.reciostore.domain.model.Product
import retrofit2.Response
import retrofit2.http.*

interface ProductsService {
    @GET(Config.PRODUCTS_URL)
    suspend fun getProducts(): Response<List<Product>>

    @GET("${Config.PRODUCTS_URL}/getProductsByCategory/{id_category}")
    suspend fun getProductsByCategory(@Path("id_category") id: String): Response<List<Product>>

    @GET("${Config.PRODUCTS_URL}/getProductsByName/{name}")
    suspend fun getProductsByName(@Path("name") name: String): Response<List<Product>>

    @POST("${Config.PRODUCTS_URL}/createProduct")
    suspend fun createProduct(@Body product: Product): Response<Product>

    @PUT("${Config.PRODUCTS_URL}/updateProduct/{id}")
    suspend fun updateProduct(@Path("id") id: String, @Body product: Product): Response<Product>

    @DELETE("${Config.PRODUCTS_URL}/deleteProduct/{id}")
    suspend fun deleteProduct(@Path("id") id: String): Response<Product>
}