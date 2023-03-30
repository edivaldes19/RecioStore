package com.edival.reciostore.data.dataSource.local

import com.edival.reciostore.data.dataSource.local.dataStore.AuthDataStore
import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
import kotlinx.coroutines.flow.Flow

class AuthLocalDataSourceImpl constructor(private val authDataStore: AuthDataStore) :
    AuthLocalDataSource {
    override suspend fun saveSession(authResponse: AuthResponse) {
        authDataStore.saveUser(authResponse)
    }

    override suspend fun updateSession(user: User) {
        authDataStore.updateUser(user)
    }

    override suspend fun logOut() = authDataStore.clearData()
    override fun getSessionData(): Flow<AuthResponse> = authDataStore.getUser()
}