package com.edival.reciostore.data.dataSource.remote.service

import com.edival.reciostore.domain.model.User
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface UsersService {
    @PUT("users/updateData/{id}")
    suspend fun updateData(@Path("id") id: String, @Body user: User): Response<User>

    @Multipart
    @PUT("users/updateImage/{id}")
    suspend fun updateImage(@Path("id") id: String, @Part file: MultipartBody.Part): Response<User>
}