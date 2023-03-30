package com.edival.reciostore.data.dataSource.local

import com.edival.reciostore.data.dataSource.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface CategoriesLocalDataSource {
    suspend fun insertCategory(category: CategoryEntity)
    suspend fun insertAllCategories(categories: List<CategoryEntity>)
    fun getCategories(): Flow<List<CategoryEntity>>
    suspend fun updateCategory(id: String, name: String, description: String, img: String)
    suspend fun deleteCategory(id: String)
}