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
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource
) : AuthRepository {
    override suspend fun logIn(email: String, password: String): Resource<AuthResponse> {
        return ResponseToRequest.send(authRemoteDataSource.logIn(email, password))
    }

    override suspend fun signUp(user: User): Resource<AuthResponse> {
        return ResponseToRequest.send(authRemoteDataSource.signUp(user))
    }

    override suspend fun saveSession(authResponse: AuthResponse) {
        authLocalDataSource.saveSession(authResponse)
    }

    override suspend fun updateSession(user: User) = authLocalDataSource.updateSession(user)
    override suspend fun logOut() = authLocalDataSource.logOut()

    override fun getSessionData(): Flow<AuthResponse> = authLocalDataSource.getSessionData()
}