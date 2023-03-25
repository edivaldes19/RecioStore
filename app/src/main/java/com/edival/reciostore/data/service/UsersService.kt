package com.edival.reciostore.data.service

import com.edival.reciostore.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface UsersService {
    @PUT("users/updateData/{id}")
    suspend fun updateData(@Path("id") id: String, @Body user: User): Response<User>
}