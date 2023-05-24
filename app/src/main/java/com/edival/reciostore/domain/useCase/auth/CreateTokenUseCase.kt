package com.edival.reciostore.domain.useCase.auth

import com.edival.reciostore.domain.repository.AuthRepository
import com.edival.reciostore.domain.util.Resource

class CreateTokenUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): Resource<String> = authRepository.createToken()
}