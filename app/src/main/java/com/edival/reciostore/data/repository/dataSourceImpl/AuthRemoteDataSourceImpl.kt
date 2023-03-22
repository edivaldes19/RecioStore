package com.edival.reciostore.data.repository.dataSourceImpl

import com.edival.reciostore.data.repository.dataSource.AuthRemoteDataSource
import com.edival.reciostore.data.service.AuthService
import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
import retrofit2.Response

class AuthRemoteDataSourceImpl(private val authService: AuthService) : AuthRemoteDataSource {
    override suspend fun logIn(email: String, password: String): Response<AuthResponse> {
        return authService.logIn(email, password)
    }

    override suspend fun signUp(user: User): Response<AuthResponse> = authService.signUp(user)
}