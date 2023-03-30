package com.edival.reciostore.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edival.reciostore.data.dataSource.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCategories(categories: List<CategoryEntity>)

    @Query("SELECT * FROM categories")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Query("UPDATE categories SET name = :name, description = :description, img = :img WHERE id = :id")
    suspend fun updateCategory(id: String, name: String, description: String, img: String)

    @Query("DELETE FROM categories WHERE id = :id")
    suspend fun deleteCategory(id: String)
}