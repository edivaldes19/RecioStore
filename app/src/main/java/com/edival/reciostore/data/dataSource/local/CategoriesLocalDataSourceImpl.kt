package com.edival.reciostore.data.dataSource.local

import com.edival.reciostore.data.dataSource.local.dao.CategoriesDAO
import com.edival.reciostore.data.dataSource.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

class CategoriesLocalDataSourceImpl(private val categoriesDAO: CategoriesDAO) :
    CategoriesLocalDataSource {
    override suspend fun insertCategory(category: CategoryEntity) {
        return categoriesDAO.insertCategory(category)
    }

    override suspend fun insertAllCategories(categories: List<CategoryEntity>) {
        return categoriesDAO.insertAllCategories(categories)
    }

    override fun getCategories(): Flow<List<CategoryEntity>> = categoriesDAO.getCategories()
    override suspend fun updateCategory(
        id: String, name: String, description: String, img: String
    ) = categoriesDAO.updateCategory(id, name, description, img)

    override suspend fun deleteCategory(id: String) = categoriesDAO.deleteCategory(id)
}