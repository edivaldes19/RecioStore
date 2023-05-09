package com.edival.reciostore.data.dataSource.local

import com.edival.reciostore.data.dataSource.local.dataStore.AuthDataStore
import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
import kotlinx.coroutines.flow.Flow

class AuthLocalDataSourceImpl constructor(private val authDataStore: AuthDataStore) :
    AuthLocalDataSource {
    override suspend fun saveUser(authResponse: AuthResponse) {
        authDataStore.saveUser(authResponse)
    }

    override suspend fun saveRoleName(name: String) {
        authDataStore.saveRoleName(name)
    }

    override suspend fun updateUser(user: User) {
        authDataStore.updateUser(user)
    }

    override suspend fun logOut() = authDataStore.logOut()
    override fun getUser(): Flow<AuthResponse> = authDataStore.getUser()
    override fun getRoleName(): Flow<String?> = authDataStore.getRoleName()
}