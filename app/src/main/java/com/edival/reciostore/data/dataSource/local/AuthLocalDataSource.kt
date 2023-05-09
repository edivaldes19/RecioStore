package com.edival.reciostore.data.dataSource.local

import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    suspend fun saveUser(authResponse: AuthResponse)
    suspend fun saveRoleName(name: String)
    suspend fun updateUser(user: User)
    suspend fun logOut()
    fun getUser(): Flow<AuthResponse>
    fun getRoleName(): Flow<String?>
}