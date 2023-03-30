package com.edival.reciostore.data.dataSource.local

import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    suspend fun saveSession(authResponse: AuthResponse)
    suspend fun updateSession(user: User)
    suspend fun logOut()
    fun getSessionData(): Flow<AuthResponse>
}