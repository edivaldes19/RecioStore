package com.edival.reciostore.data.dataSource.remote.service

import com.edival.reciostore.core.Config
import com.edival.reciostore.domain.model.Category
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface CategoriesService {
    @GET(Config.CATEGORIES_URL)
    suspend fun getCategories(): Response<List<Category>>

    @POST("${Config.CATEGORIES_URL}/createCategory")
    suspend fun createCategory(
        @Part file: MultipartBody.Part, @Part name: RequestBody, @Part description: RequestBody
    ): Response<Category>

    @PUT("${Config.CATEGORIES_URL}/updateCategory/{id}")
    suspend fun updateCategory(@Path("id") id: String, @Body category: Category): Response<Category>

    @PUT("${Config.CATEGORIES_URL}/updateCategoryImage/{id}")
    suspend fun updateCategoryImage(
        @Path("id") id: String, @Part file: MultipartBody.Part
    ): Response<Category>

    @DELETE("${Config.CATEGORIES_URL}/deleteCategory/{id}")
    suspend fun deleteCategory(@Path("id") id: String): Response<Unit>
}