package com.edival.reciostore.domain.useCase.auth

import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.repository.AuthRepository
import com.edival.reciostore.domain.util.Resource

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Resource<AuthResponse> {
        return authRepository.logIn(email, password)
    }
}