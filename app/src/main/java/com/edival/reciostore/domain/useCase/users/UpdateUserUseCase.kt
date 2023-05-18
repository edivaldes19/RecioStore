package com.edival.reciostore.domain.useCase.users

import android.net.Uri
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.repository.UsersRepository
import com.edival.reciostore.domain.util.Resource

class UpdateUserUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(id: String, user: User, uri: Uri?): Resource<User> {
        return usersRepository.updateUser(id, user, uri)
    }
}