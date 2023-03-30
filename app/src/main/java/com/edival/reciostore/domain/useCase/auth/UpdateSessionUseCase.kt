package com.edival.reciostore.domain.useCase.auth

import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.repository.AuthRepository

class UpdateSessionUseCase constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(user: User) = repository.updateSession(user)
}