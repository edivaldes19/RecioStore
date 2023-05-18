package com.edival.reciostore.domain.useCase.auth

import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.repository.AuthRepository
import com.edival.reciostore.domain.util.Resource

class UpdatePasswordUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(
        id: String, oldPassword: String, newPassword: String
    ): Resource<User> = repository.updatePassword(id, oldPassword, newPassword)
}