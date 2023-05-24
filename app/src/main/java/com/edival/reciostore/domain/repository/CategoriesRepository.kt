package com.edival.reciostore.domain.repository

import android.content.Context
import android.net.Uri
import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    fun getCategories(): Flow<Resource<List<Category>>>
    suspend fun createCategory(category: Category, uri: Uri): Resource<Category>
    suspend fun updateCategory(id: String, category: Category, uri: Uri?): Resource<Category>
    suspend fun deleteCategory(id: String): Resource<Unit>
    suspend fun downloadCtgImg(ctx: Context, url: String): Resource<Unit>
}