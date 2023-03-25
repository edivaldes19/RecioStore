package com.edival.reciostore.data.repository.dataSource

import com.edival.reciostore.domain.model.User
import retrofit2.Response

interface UsersRemoteDataSource {
    suspend fun updateData(id: String, user: User): Response<User>
}