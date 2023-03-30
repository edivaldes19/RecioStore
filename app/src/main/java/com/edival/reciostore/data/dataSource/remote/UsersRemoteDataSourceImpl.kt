package com.edival.reciostore.data.dataSource.remote

import com.edival.reciostore.data.dataSource.remote.service.UsersService
import com.edival.reciostore.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Response
import java.io.File

class UsersRemoteDataSourceImpl(private val usersService: UsersService) : UsersRemoteDataSource {
    override suspend fun updateData(id: String, user: User): Response<User> {
        return usersService.updateData(id, user)
    }

    override suspend fun updateImage(id: String, file: File): Response<User> {
        val connection = withContext(Dispatchers.IO) {
            file.toURI().toURL().openConnection()
        }
        val mimeType = connection.contentType
        val requestFile = file.asRequestBody(mimeType.toMediaTypeOrNull())
        val fileFormData = MultipartBody.Part.createFormData("file", file.name, requestFile)
        return usersService.updateImage(id, fileFormData)
    }
}