package com.edival.reciostore.domain.useCase.users

import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.repository.UsersRepository
import com.edival.reciostore.domain.util.Resource

class UpdateUserToClientUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(id: String): Resource<AuthResponse> {
        return usersRepository.updateUserToClient(id)
    }
}