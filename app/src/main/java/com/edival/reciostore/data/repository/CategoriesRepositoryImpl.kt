package com.edival.reciostore.data.repository

import android.util.Log
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
    private val localDS: CategoriesLocalDataSource, private val remoteDS: CategoriesRemoteDataSource
) : CategoriesRepository {
    override fun getCategories(): Flow<Resource<List<Category>>> = flow {
        localDS.getCategories().collect { categoryEntities ->
            categoryEntities.run {
                val categoriesLocalMap = this.map { categoryEntity -> categoryEntity.toCategory() }
                try {
                    ResponseToRequest.send(remoteDS.getCategories()).run {
                        when (this) {
                            is Resource.Success -> {
                                val categoriesRemote = this.data
                                if (!isListEqual(categoriesRemote, categoriesLocalMap)) {
                                    localDS.insertAllCategories(categoriesRemote.map { category -> category.toCategoryEntity() })
                                }
                                emit(Resource.Success(categoriesRemote))
                                Log.d("getCategories", "LOCAL: $categoriesLocalMap")
                                Log.d("getCategories", "REMOTE: $categoriesRemote")
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
        ResponseToRequest.send(remoteDS.createCategory(file, category)).run {
            return when (this) {
                is Resource.Success -> {
                    localDS.insertCategory(this.data.toCategoryEntity())
                    Resource.Success(this.data)
                }

                else -> Resource.Failure()
            }
        }
    }

    override suspend fun updateCategory(id: String, category: Category): Resource<Category> {
        ResponseToRequest.send(remoteDS.updateCategory(id, category)).run {
            return when (this) {
                is Resource.Success -> {
                    localDS.updateCategory(
                        id = this.data.id ?: "",
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
        ResponseToRequest.send(remoteDS.updateCategoryImage(id, file)).run {
            return when (this) {
                is Resource.Success -> {
                    localDS.updateCategory(
                        id = this.data.id ?: "",
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
        ResponseToRequest.send(remoteDS.deleteCategory(id)).run {
            return when (this) {
                is Resource.Success -> {
                    localDS.deleteCategory(id)
                    Resource.Success(Unit)
                }

                else -> Resource.Failure()
            }
        }
    }
}