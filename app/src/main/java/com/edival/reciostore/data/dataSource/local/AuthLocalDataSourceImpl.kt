package com.edival.reciostore.data.dataSource.local

import com.edival.reciostore.data.dataSource.local.dataStore.AuthDataStore
import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
import kotlinx.coroutines.flow.Flow

class AuthLocalDataSourceImpl constructor(private val authDataStore: AuthDataStore) :
    AuthLocalDataSource {
    override suspend fun saveAccount(authResponse: AuthResponse) {
        authDataStore.saveAccount(authResponse)
    }

    override suspend fun updateAccount(user: User) = authDataStore.updateAccount(user)
    override suspend fun logOut() = authDataStore.logOut()
    override fun getAccount(): Flow<AuthResponse> = authDataStore.getAccount()
}