package com.edival.reciostore.domain.useCase.auth

import com.edival.reciostore.domain.repository.AuthRepository
import com.edival.reciostore.domain.util.Resource

class DeleteAccountUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(id: String): Resource<Unit> = repository.deleteAccount(id)
}