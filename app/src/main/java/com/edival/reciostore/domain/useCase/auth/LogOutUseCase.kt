package com.edival.reciostore.domain.useCase.auth

import com.edival.reciostore.domain.repository.AuthRepository

class LogOutUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke() = repository.logOut()
}