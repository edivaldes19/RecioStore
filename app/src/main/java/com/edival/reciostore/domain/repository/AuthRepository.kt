package com.edival.reciostore.domain.repository

import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signUp(user: User): Resource<AuthResponse>
    suspend fun logIn(email: String, password: String): Resource<AuthResponse>
    suspend fun saveUser(authResponse: AuthResponse)
    suspend fun saveRoleName(name: String)
    suspend fun updateUser(user: User)
    suspend fun logOut()
    fun getUser(): Flow<AuthResponse>
    fun getRoleName(): Flow<String?>
}