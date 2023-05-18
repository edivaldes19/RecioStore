package com.edival.reciostore.data.dataSource.remote

import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.domain.model.DeleteResponse
import retrofit2.Response

interface CategoriesRemoteDataSource {
    suspend fun getCategories(): Response<List<Category>>
    suspend fun createCategory(category: Category): Response<Category>
    suspend fun updateCategory(id: String, category: Category): Response<Category>
    suspend fun deleteCategory(id: String): Response<DeleteResponse>
}