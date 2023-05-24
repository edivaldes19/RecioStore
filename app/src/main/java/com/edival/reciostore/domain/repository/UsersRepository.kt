package com.edival.reciostore.domain.repository

import android.content.Context
import android.net.Uri
import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    fun getUsers(id: String): Flow<Resource<List<User>>>
    suspend fun updateUser(id: String, user: User, uri: Uri?): Resource<User>
    suspend fun updateUserToClient(id: String): Resource<AuthResponse>
    suspend fun updateUserToAdmin(id: String): Resource<AuthResponse>
    suspend fun downloadUserImg(ctx: Context, url: String): Resource<Unit>
}