package com.edival.reciostore.domain.useCase.auth

import com.edival.reciostore.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class GetRoleNameUseCase(private val repository: AuthRepository) {
    operator fun invoke(): Flow<String?> = repository.getRoleName()
}