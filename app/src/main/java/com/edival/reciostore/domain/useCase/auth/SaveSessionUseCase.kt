package com.edival.reciostore.domain.useCase.auth

import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.repository.AuthRepository

class SaveSessionUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(authResponse: AuthResponse) = repository.saveAccount(authResponse)
}