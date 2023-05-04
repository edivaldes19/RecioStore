package com.edival.reciostore.data.repository

import com.edival.reciostore.data.dataSource.remote.UsersRemoteDataSource
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.repository.UsersRepository
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.domain.util.ResponseToRequest
import java.io.File

class UsersRepositoryImpl(private val remoteDS: UsersRemoteDataSource) : UsersRepository {
    override suspend fun updateUser(id: String, user: User): Resource<User> {
        return ResponseToRequest.send(remoteDS.updateUser(id, user))
    }

    override suspend fun updateUserImage(id: String, file: File): Resource<User> {
        return ResponseToRequest.send(remoteDS.updateUserImage(id, file))
    }
}