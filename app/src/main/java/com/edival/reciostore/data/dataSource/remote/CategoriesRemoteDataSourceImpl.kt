package com.edival.reciostore.data.dataSource.remote

import com.edival.reciostore.core.Config
import com.edival.reciostore.data.dataSource.remote.service.CategoriesService
import com.edival.reciostore.domain.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File

class CategoriesRemoteDataSourceImpl(private val categoriesService: CategoriesService) :
    CategoriesRemoteDataSource {
    override suspend fun getCategories(): Response<List<Category>> {
        return categoriesService.getCategories()
    }

    override suspend fun createCategory(file: File, category: Category): Response<Category> {
        val connection = withContext(Dispatchers.IO) { file.toURI().toURL().openConnection() }
        val mimeType = connection.contentType
        val requestFile = file.asRequestBody(mimeType.toMediaTypeOrNull())
        val fileFormData = MultipartBody.Part.createFormData("file", file.name, requestFile)
        val nameData = category.name!!.toRequestBody(Config.TEXT_MT.toMediaTypeOrNull())
        val descriptionData =
            category.description!!.toRequestBody(Config.TEXT_MT.toMediaTypeOrNull())
        return categoriesService.createCategory(fileFormData, nameData, descriptionData)
    }

    override suspend fun updateCategory(id: String, category: Category): Response<Category> {
        return categoriesService.updateCategory(id, category)
    }

    override suspend fun updateCategoryImage(id: String, file: File): Response<Category> {
        val connection = withContext(Dispatchers.IO) { file.toURI().toURL().openConnection() }
        val mimeType = connection.contentType
        val requestFile = file.asRequestBody(mimeType.toMediaTypeOrNull())
        val fileFormData = MultipartBody.Part.createFormData("file", file.name, requestFile)
        return categoriesService.updateCategoryImage(id, fileFormData)
    }

    override suspend fun deleteCategory(id: String): Response<Unit> {
        return categoriesService.deleteCategory(id)
    }
}