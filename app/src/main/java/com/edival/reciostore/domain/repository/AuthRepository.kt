package com.edival.reciostore.domain.repository

import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun createToken(): Resource<String>
    suspend fun signUp(user: User): Resource<AuthResponse>
    suspend fun logIn(email: String, password: String): Resource<AuthResponse>
    suspend fun updatePassword(id: String, oldPassword: String, newPassword: String): Resource<User>
    suspend fun updateNotificationToken(id: String, notification_token: String): Resource<User>
    suspend fun deleteAccount(id: String): Resource<Unit>
    suspend fun saveAccount(authResponse: AuthResponse)
    suspend fun updateAccount(user: User)
    suspend fun logOut()
    fun getAccount(): Flow<AuthResponse>
}