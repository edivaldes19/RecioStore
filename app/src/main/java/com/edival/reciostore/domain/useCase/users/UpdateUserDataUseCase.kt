package com.edival.reciostore.domain.useCase.users

import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.repository.UsersRepository
import com.edival.reciostore.domain.util.Resource

class UpdateUserDataUseCase constructor(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(id: String, user: User): Resource<User> {
        return usersRepository.updateData(id, user)
    }
}