package com.edival.reciostore.data.dataSource.local

import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    suspend fun saveAccount(authResponse: AuthResponse)
    suspend fun updateAccount(user: User)
    suspend fun logOut()
    fun getAccount(): Flow<AuthResponse>
}