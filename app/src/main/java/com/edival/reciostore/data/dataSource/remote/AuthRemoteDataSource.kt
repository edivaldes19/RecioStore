package com.edival.reciostore.data.dataSource.remote

import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
import retrofit2.Response

interface AuthRemoteDataSource {
    suspend fun signUp(user: User): Response<AuthResponse>
    suspend fun logIn(email: String, password: String): Response<AuthResponse>
    suspend fun updatePassword(id: String, oldPassword: String, newPassword: String): Response<User>
    suspend fun deleteAccount(id: String): Response<Unit>
}