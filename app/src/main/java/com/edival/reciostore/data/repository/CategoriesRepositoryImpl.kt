package com.edival.reciostore.data.repository

import android.net.Uri
import android.util.Log
import com.edival.reciostore.core.Config
import com.edival.reciostore.data.dataSource.local.CategoriesLocalDataSource
import com.edival.reciostore.data.dataSource.remote.CategoriesRemoteDataSource
import com.edival.reciostore.data.mapper.toCategory
import com.edival.reciostore.data.mapper.toCategoryEntity
import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.domain.repository.CategoriesRepository
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.domain.util.ResponseToRequest
import com.edival.reciostore.domain.util.isListEqual
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class CategoriesRepositoryImpl @Inject constructor(
    private val localDS: CategoriesLocalDataSource,
    private val remoteDS: CategoriesRemoteDataSource,
    private val storage: FirebaseStorage,
    @Named(Config.CATEGORIES_URL) private val storageCategoriesRef: StorageReference
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

    override suspend fun createCategory(category: Category, uri: Uri): Resource<Category> {
        try {
            val currentTime = "${System.currentTimeMillis() / 1000}"
            val ref = storageCategoriesRef.child("${category.name}_$currentTime")
            ref.putFile(uri).await()
            ref.downloadUrl.await().also { dlUri -> category.img = dlUri.toString() }
        } catch (e: Exception) {
            return if (!e.message.isNullOrBlank()) Resource.Failure(e.message!!) else Resource.Failure()
        }
        ResponseToRequest.send(remoteDS.createCategory(category)).run {
            return when (this) {
                is Resource.Success -> {
                    localDS.insertCategory(this.data.toCategoryEntity())
                    Resource.Success(this.data)
                }

                else -> Resource.Failure()
            }
        }
    }

    override suspend fun updateCategory(
        id: String, category: Category, uri: Uri?
    ): Resource<Category> {
        try {
            uri?.let { photo ->
                if (!category.img.isNullOrBlank()) {
                    val httpsReference = storage.getReferenceFromUrl(category.img!!)
                    httpsReference.delete().await()
                }
                val currentTime = "${System.currentTimeMillis() / 1000}"
                val ref = storageCategoriesRef.child("${category.name}_$currentTime")
                ref.putFile(photo).await()
                ref.downloadUrl.await().also { dlUri -> category.img = dlUri.toString() }
            }
        } catch (e: Exception) {
            return if (!e.message.isNullOrBlank()) Resource.Failure(e.message!!) else Resource.Failure()
        }
        ResponseToRequest.send(remoteDS.updateCategory(id, category)).run {
            return when (this) {
                is Resource.Success -> {
                    localDS.updateCategory(
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
        ResponseToRequest.send(remoteDS.deleteCategory(id)).run {
            return when (this) {
                is Resource.Success -> {
                    localDS.deleteCategory(id)
                    if (!this.data.url.isNullOrBlank()) {
                        val httpsReference = storage.getReferenceFromUrl(this.data.url)
                        httpsReference.delete().await()
                    }
                    Resource.Success(Unit)
                }

                else -> Resource.Failure()
            }
        }
    }
}