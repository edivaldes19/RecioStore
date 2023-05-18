package com.edival.reciostore.data.repository

import com.edival.reciostore.data.dataSource.local.AuthLocalDataSource
import com.edival.reciostore.data.dataSource.remote.AuthRemoteDataSource
import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.repository.AuthRepository
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.domain.util.ResponseToRequest
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val localDS: AuthLocalDataSource, private val remoteDS: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun logIn(email: String, password: String): Resource<AuthResponse> {
        return ResponseToRequest.send(remoteDS.logIn(email, password))
    }

    override suspend fun signUp(user: User): Resource<AuthResponse> {
        return ResponseToRequest.send(remoteDS.signUp(user))
    }

    override suspend fun updatePassword(
        id: String, oldPassword: String, newPassword: String
    ): Resource<User> {
        return ResponseToRequest.send(remoteDS.updatePassword(id, oldPassword, newPassword))
    }

    override suspend fun deleteAccount(id: String): Resource<Unit> {
        return ResponseToRequest.send(remoteDS.deleteAccount(id))
    }

    override suspend fun saveUser(authResponse: AuthResponse) = localDS.saveUser(authResponse)
    override suspend fun saveRoleName(name: String) = localDS.saveRoleName(name)
    override suspend fun updateUser(user: User) = localDS.updateUser(user)
    override suspend fun logOut() = localDS.logOut()
    override fun getUser(): Flow<AuthResponse> = localDS.getUser()
    override fun getRoleName(): Flow<String?> = localDS.getRoleName()
}