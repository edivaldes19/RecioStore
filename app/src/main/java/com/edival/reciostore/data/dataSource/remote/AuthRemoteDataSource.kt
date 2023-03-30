package com.edival.reciostore.data.dataSource.remote

import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
import retrofit2.Response

interface AuthRemoteDataSource {
    suspend fun logIn(email: String, password: String): Response<AuthResponse>
    suspend fun signUp(user: User): Response<AuthResponse>
}