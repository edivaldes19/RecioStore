package com.edival.reciostore.domain.repository

import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.util.Resource
import java.io.File

interface UsersRepository {
    suspend fun updateUser(id: String, user: User): Resource<User>
    suspend fun updateUserImage(id: String, file: File): Resource<User>
}