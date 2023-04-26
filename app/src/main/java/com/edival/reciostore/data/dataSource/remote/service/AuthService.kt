package com.edival.reciostore.data.dataSource.remote.service

import com.edival.reciostore.core.Config
import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {
    @POST("${Config.AUTH_URL}/signup")
    suspend fun signUp(@Body user: User): Response<AuthResponse>

    @FormUrlEncoded
    @POST("${Config.AUTH_URL}/login")
    suspend fun logIn(
        @Field("email") email: String, @Field("password") password: String
    ): Response<AuthResponse>
}