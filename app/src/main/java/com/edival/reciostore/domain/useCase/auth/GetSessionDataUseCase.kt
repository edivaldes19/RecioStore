package com.edival.reciostore.domain.useCase.auth

import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class GetSessionDataUseCase(private val repository: AuthRepository) {
    operator fun invoke(): Flow<AuthResponse> = repository.getAccount()
}