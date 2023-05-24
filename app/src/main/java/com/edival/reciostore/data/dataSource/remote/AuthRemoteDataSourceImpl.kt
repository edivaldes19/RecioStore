package com.edival.reciostore.data.dataSource.remote

import com.edival.reciostore.data.dataSource.remote.service.AuthService
import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.DeleteResponse
import com.edival.reciostore.domain.model.User
import retrofit2.Response

class AuthRemoteDataSourceImpl(private val authService: AuthService) : AuthRemoteDataSource {
    override suspend fun signUp(user: User): Response<AuthResponse> = authService.signUp(user)
    override suspend fun logIn(email: String, password: String): Response<AuthResponse> {
        return authService.logIn(email, password)
    }

    override suspend fun updatePassword(
        id: String, oldPassword: String, newPassword: String
    ): Response<User> = authService.updatePassword(id, oldPassword, newPassword)

    override suspend fun updateNotificationToken(
        id: String, notification_token: String
    ): Response<User> = authService.updateNotificationToken(id, notification_token)

    override suspend fun deleteAccount(id: String): Response<DeleteResponse> {
        return authService.deleteAccount(id)
    }
}