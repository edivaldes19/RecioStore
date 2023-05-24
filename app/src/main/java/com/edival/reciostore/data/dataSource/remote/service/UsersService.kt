package com.edival.reciostore.data.dataSource.remote.service

import com.edival.reciostore.core.Config
import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface UsersService {
    @GET("${Config.USERS_URL}/{id}")
    suspend fun getUsers(@Path("id") id: String): Response<List<User>>

    @PUT("${Config.USERS_URL}/updateUser/{id}")
    suspend fun updateUser(@Path("id") id: String, @Body user: User): Response<User>

    @Multipart
    @PUT("${Config.USERS_URL}/updateUserImage/{id}")
    suspend fun updateUserImage(
        @Path("id") id: String, @Part file: MultipartBody.Part
    ): Response<User>

    @PUT("${Config.USERS_URL}/updateUserToClient/{id}")
    suspend fun updateUserToClient(@Path("id") id: String): Response<AuthResponse>

    @PUT("${Config.USERS_URL}/updateUserToAdmin/{id}")
    suspend fun updateUserToAdmin(@Path("id") id: String): Response<AuthResponse>
}