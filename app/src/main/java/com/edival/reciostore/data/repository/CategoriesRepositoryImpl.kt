package com.edival.reciostore.data.repository

import com.edival.reciostore.data.dataSource.local.CategoriesLocalDataSource
import com.edival.reciostore.data.dataSource.remote.CategoriesRemoteDataSource
import com.edival.reciostore.data.mapper.toCategory
import com.edival.reciostore.data.mapper.toCategoryEntity
import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.domain.repository.CategoriesRepository
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.domain.util.ResponseToRequest
import com.edival.reciostore.domain.util.isListEqual
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File

class CategoriesRepositoryImpl(
    private val categoriesLocalDataSource: CategoriesLocalDataSource,
    private val categoriesRemoteDataSource: CategoriesRemoteDataSource
) : CategoriesRepository {
    override fun getCategories(): Flow<Resource<List<Category>>> = flow {
        categoriesLocalDataSource.getCategories().collect { data ->
            data.run {
                val categoriesLocalMap = this.map { categoryEntity -> categoryEntity.toCategory() }
                try {
                    ResponseToRequest.send(categoriesRemoteDataSource.getCategories()).run {
                        when (this) {
                            is Resource.Success -> {
                                if (!isListEqual(this.data, categoriesLocalMap)) {
                                    categoriesLocalDataSource.insertAllCategories(this.data.map { category -> category.toCategoryEntity() })
                                }
                                emit(Resource.Success(this.data))
                            }
                            is Resource.Failure -> emit(Resource.Success(categoriesLocalMap))
                            else -> emit(Resource.Success(categoriesLocalMap))
                        }
                    }
                } catch (e: Exception) {
                    emit(Resource.Success(categoriesLocalMap))
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun createCategory(file: File, category: Category): Resource<Category> {
        ResponseToRequest.send(categoriesRemoteDataSource.createCategory(file, category)).run {
            return when (this) {
                is Resource.Success -> {
                    categoriesLocalDataSource.insertCategory(this.data.toCategoryEntity())
                    Resource.Success(this.data)
                }
                else -> Resource.Failure()
            }
        }
    }

    override suspend fun updateCategory(id: String, category: Category): Resource<Category> {
        ResponseToRequest.send(categoriesRemoteDataSource.updateCategory(id, category)).run {
            return when (this) {
                is Resource.Success -> {
                    categoriesLocalDataSource.updateCategory(
                        id = this.data.id.orEmpty(),
                        name = this.data.name.orEmpty(),
                        description = this.data.description.orEmpty(),
                        img = this.data.img.orEmpty()
                    )
                    Resource.Success(this.data)
                }
                else -> Resource.Failure()
            }
        }
    }

    override suspend fun updateCategoryImage(id: String, file: File): Resource<Category> {
        ResponseToRequest.send(categoriesRemoteDataSource.updateCategoryImage(id, file)).run {
            return when (this) {
                is Resource.Success -> {
                    categoriesLocalDataSource.updateCategory(
                        id = this.data.id.orEmpty(),
                        name = this.data.name.orEmpty(),
                        description = this.data.description.orEmpty(),
                        img = this.data.img.orEmpty()
                    )
                    Resource.Success(this.data)
                }
                else -> Resource.Failure()
            }
        }
    }

    override suspend fun deleteCategory(id: String): Resource<Unit> {
        ResponseToRequest.send(categoriesRemoteDataSource.deleteCategory(id)).run {
            return when (this) {
                is Resource.Success -> {
                    categoriesLocalDataSource.deleteCategory(id)
                    Resource.Success(Unit)
                }
                else -> Resource.Failure()
            }
        }
    }
}