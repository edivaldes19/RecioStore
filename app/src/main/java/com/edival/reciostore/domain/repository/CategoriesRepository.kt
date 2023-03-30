package com.edival.reciostore.domain.repository

import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

interface CategoriesRepository {
    fun getCategories(): Flow<Resource<List<Category>>>
    suspend fun createCategory(file: File, category: Category): Resource<Category>
    suspend fun updateCategory(id: String, category: Category): Resource<Category>
    suspend fun updateCategoryImage(id: String, file: File): Resource<Category>
    suspend fun deleteCategory(id: String): Resource<Unit>
}