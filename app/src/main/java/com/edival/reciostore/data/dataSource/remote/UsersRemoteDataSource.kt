package com.edival.reciostore.data.dataSource.remote

import com.edival.reciostore.domain.model.User
import retrofit2.Response
import java.io.File

interface UsersRemoteDataSource {
    suspend fun updateUser(id: String, user: User): Response<User>
    suspend fun updateUserImage(id: String, file: File): Response<User>
}