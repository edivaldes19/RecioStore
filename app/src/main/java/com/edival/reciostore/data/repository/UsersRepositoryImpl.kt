package com.edival.reciostore.data.repository

import com.edival.reciostore.data.repository.dataSource.UsersRemoteDataSource
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.repository.UsersRepository
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.domain.util.ResponseToRequest

class UsersRepositoryImpl(private val usersRemoteDataSource: UsersRemoteDataSource) :
    UsersRepository {
    override suspend fun updateData(id: String, user: User): Resource<User> {
        return ResponseToRequest.send(usersRemoteDataSource.updateData(id, user))
    }
}