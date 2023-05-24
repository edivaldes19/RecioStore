package com.edival.reciostore.domain.useCase.users

import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.repository.UsersRepository
import com.edival.reciostore.domain.util.Resource

class UpdateUserToAdminUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(id: String): Resource<AuthResponse> {
        return usersRepository.updateUserToAdmin(id)
    }
}