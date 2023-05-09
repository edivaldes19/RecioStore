package com.edival.reciostore.domain.useCase.auth

import com.edival.reciostore.domain.repository.AuthRepository

class SaveRoleNameUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(name: String) = repository.saveRoleName(name)
}