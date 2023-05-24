package com.edival.reciostore.domain.useCase.auth

import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.repository.AuthRepository
import com.edival.reciostore.domain.util.Resource

class UpdateNotificationTokenUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(id: String, notification_token: String): Resource<User> {
        return repository.updateNotificationToken(id, notification_token)
    }
}