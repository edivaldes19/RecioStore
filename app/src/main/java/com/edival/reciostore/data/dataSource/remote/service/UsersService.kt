package com.edival.reciostore.data.dataSource.remote.service

import com.edival.reciostore.core.Config
import com.edival.reciostore.domain.model.User
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface UsersService {
    @PUT("${Config.USERS_URL}/updateUser/{id}")
    suspend fun updateUser(@Path("id") id: String, @Body user: User): Response<User>

    @Multipart
    @PUT("${Config.USERS_URL}/updateUserImage/{id}")
    suspend fun updateUserImage(
        @Path("id") id: String,
        @Part file: MultipartBody.Part
    ): Response<User>
}