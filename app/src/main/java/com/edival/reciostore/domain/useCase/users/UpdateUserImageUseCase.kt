package com.edival.reciostore.domain.useCase.users

import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.repository.UsersRepository
import com.edival.reciostore.domain.util.Resource
import java.io.File

class UpdateUserImageUseCase constructor(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(id: String, file: File): Resource<User> {
        return usersRepository.updateUserImage(id, file)
    }
}