package com.edival.reciostore.data.repository.dataSource

import com.edival.reciostore.domain.model.AuthResponse
import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    suspend fun saveSession(authResponse: AuthResponse)
    suspend fun logOut()
    fun getSessionData(): Flow<AuthResponse>
}