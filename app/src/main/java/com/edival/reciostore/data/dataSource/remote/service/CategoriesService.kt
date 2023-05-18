package com.edival.reciostore.data.dataSource.remote.service

import com.edival.reciostore.core.Config
import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.domain.model.DeleteResponse
import retrofit2.Response
import retrofit2.http.*

interface CategoriesService {
    @GET(Config.CATEGORIES_URL)
    suspend fun getCategories(): Response<List<Category>>

    @POST("${Config.CATEGORIES_URL}/createCategory")
    suspend fun createCategory(@Body category: Category): Response<Category>

    @PUT("${Config.CATEGORIES_URL}/updateCategory/{id}")
    suspend fun updateCategory(@Path("id") id: String, @Body category: Category): Response<Category>

    @DELETE("${Config.CATEGORIES_URL}/deleteCategory/{id}")
    suspend fun deleteCategory(@Path("id") id: String): Response<DeleteResponse>
}