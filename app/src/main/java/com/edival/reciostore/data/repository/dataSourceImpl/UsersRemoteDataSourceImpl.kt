package com.edival.reciostore.data.repository.dataSourceImpl

import com.edival.reciostore.data.repository.dataSource.UsersRemoteDataSource
import com.edival.reciostore.data.service.UsersService
import com.edival.reciostore.domain.model.User
import retrofit2.Response

class UsersRemoteDataSourceImpl(private val usersService: UsersService) : UsersRemoteDataSource {
    override suspend fun updateData(id: String, user: User): Response<User> {
        return usersService.updateData(id, user)
    }
}