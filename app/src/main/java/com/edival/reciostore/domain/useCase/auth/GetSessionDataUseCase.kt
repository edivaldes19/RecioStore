package com.edival.reciostore.domain.useCase.auth

import com.edival.reciostore.domain.repository.AuthRepository

class GetSessionDataUseCase constructor(private val repository: AuthRepository) {
    operator fun invoke() = repository.getSessionData()
}