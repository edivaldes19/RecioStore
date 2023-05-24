package com.edival.reciostore.data.repository

import com.edival.reciostore.data.dataSource.local.AuthLocalDataSource
import com.edival.reciostore.data.dataSource.remote.AuthRemoteDataSource
import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.repository.AuthRepository
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.domain.util.ResponseToRequest
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val localDS: AuthLocalDataSource,
    private val remoteDS: AuthRemoteDataSource,
    private val messaging: FirebaseMessaging,
    private val storage: FirebaseStorage
) : AuthRepository {
    override suspend fun createToken(): Resource<String> {
        return try {
            val token = messaging.token.await()
            Resource.Success(token)
        } catch (e: Exception) {
            if (!e.message.isNullOrBlank()) Resource.Failure(e.message!!) else Resource.Failure()
        }
    }

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

    override suspend fun updateNotificationToken(
        id: String, notification_token: String
    ): Resource<User> {
        return ResponseToRequest.send(remoteDS.updateNotificationToken(id, notification_token))
    }

    override suspend fun deleteAccount(id: String): Resource<Unit> {
        ResponseToRequest.send(remoteDS.deleteAccount(id)).run {
            return when (this) {
                is Resource.Success -> {
                    if (!this.data.url.isNullOrBlank()) {
                        val httpsReference = storage.getReferenceFromUrl(this.data.url)
                        httpsReference.delete().await()
                    }
                    Resource.Success(Unit)
                }

                else -> Resource.Failure()
            }
        }
    }

    override suspend fun saveAccount(authResponse: AuthResponse) = localDS.saveAccount(authResponse)
    override suspend fun updateAccount(user: User) = localDS.updateAccount(user)
    override suspend fun logOut() = localDS.logOut()
    override fun getAccount(): Flow<AuthResponse> = localDS.getAccount()
}