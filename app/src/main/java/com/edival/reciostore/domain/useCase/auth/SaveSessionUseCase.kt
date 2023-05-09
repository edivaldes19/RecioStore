package com.edival.reciostore.domain.useCase.auth

import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.repository.AuthRepository

class SaveSessionUseCase constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(authResponse: AuthResponse) = repository.saveUser(authResponse)
}