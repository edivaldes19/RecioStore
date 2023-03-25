package com.edival.reciostore.data.repository.dataSourceImpl

import com.edival.reciostore.data.dataStore.AuthDataStore
import com.edival.reciostore.data.repository.dataSource.AuthLocalDataSource
import com.edival.reciostore.domain.model.AuthResponse
import kotlinx.coroutines.flow.Flow

class AuthLocalDataSourceImpl constructor(private val authDataStore: AuthDataStore) :
    AuthLocalDataSource {
    override suspend fun saveSession(authResponse: AuthResponse) {
        authDataStore.saveUser(authResponse)
    }

    override suspend fun logOut() = authDataStore.clearData()
    override fun getSessionData(): Flow<AuthResponse> = authDataStore.getUser()
}