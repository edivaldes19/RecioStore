package com.edival.reciostore.domain.useCase.users

data class UsersUseCase(
    val getUsersUseCase: GetUsersUseCase,
    val updateUserUseCase: UpdateUserUseCase,
    val updateUserToClientUseCase: UpdateUserToClientUseCase,
    val updateUserToAdminUseCase: UpdateUserToAdminUseCase
)