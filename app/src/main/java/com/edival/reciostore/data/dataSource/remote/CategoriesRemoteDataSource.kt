package com.edival.reciostore.data.dataSource.remote

import com.edival.reciostore.domain.model.Category
import retrofit2.Response
import java.io.File

interface CategoriesRemoteDataSource {
    suspend fun getCategories(): Response<List<Category>>
    suspend fun createCategory(file: File, category: Category): Response<Category>
    suspend fun updateCategory(id: String, category: Category): Response<Category>
    suspend fun updateCategoryImage(id: String, file: File): Response<Category>
    suspend fun deleteCategory(id: String): Response<Unit>
}