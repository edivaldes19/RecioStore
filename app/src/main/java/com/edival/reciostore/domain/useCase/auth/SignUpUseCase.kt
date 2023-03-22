package com.edival.reciostore.domain.useCase.auth

import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.repository.AuthRepository
import com.edival.reciostore.domain.util.Resource

class SignUpUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(user: User): Resource<AuthResponse> = authRepository.signUp(user)
}