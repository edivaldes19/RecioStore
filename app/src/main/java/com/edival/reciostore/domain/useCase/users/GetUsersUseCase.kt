package com.edival.reciostore.domain.useCase.users

import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.repository.UsersRepository
import com.edival.reciostore.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(private val usersRepository: UsersRepository) {
    operator fun invoke(id: String): Flow<Resource<List<User>>> = usersRepository.getUsers(id)
}