package com.edival.reciostore.data.dataSource.remote.service

import com.edival.reciostore.core.Config
import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.DeleteResponse
import com.edival.reciostore.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AuthService {
    @POST("${Config.AUTH_URL}/signUp")
    suspend fun signUp(@Body user: User): Response<AuthResponse>

    @FormUrlEncoded
    @POST("${Config.AUTH_URL}/logIn")
    suspend fun logIn(
        @Field("email") email: String, @Field("password") password: String
    ): Response<AuthResponse>

    @FormUrlEncoded
    @PUT("${Config.AUTH_URL}/updatePassword/{id}")
    suspend fun updatePassword(
        @Path("id") id: String,
        @Field("oldPassword") oldPassword: String,
        @Field("newPassword") newPassword: String
    ): Response<User>

    @FormUrlEncoded
    @PUT("${Config.AUTH_URL}/updateNotificationToken/{id}")
    suspend fun updateNotificationToken(
        @Path("id") id: String, @Field("notification_token") notification_token: String
    ): Response<User>

    @DELETE("${Config.AUTH_URL}/deleteAccount/{id}")
    suspend fun deleteAccount(@Path("id") id: String): Response<DeleteResponse>
}