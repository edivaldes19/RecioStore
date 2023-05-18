package com.edival.reciostore.data.dataSource.remote

import com.edival.reciostore.data.dataSource.remote.service.CategoriesService
import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.domain.model.DeleteResponse
import retrofit2.Response

class CategoriesRemoteDataSourceImpl(private val categoriesService: CategoriesService) :
    CategoriesRemoteDataSource {
    override suspend fun getCategories(): Response<List<Category>> {
        return categoriesService.getCategories()
    }

    override suspend fun createCategory(category: Category): Response<Category> {
        return categoriesService.createCategory(category)
    }

    override suspend fun updateCategory(id: String, category: Category): Response<Category> {
        return categoriesService.updateCategory(id, category)
    }

    override suspend fun deleteCategory(id: String): Response<DeleteResponse> {
        return categoriesService.deleteCategory(id)
    }
}