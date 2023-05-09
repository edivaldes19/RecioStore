package com.edival.reciostore.domain.useCase.auth

data class AuthUseCase(
    val loginUseCase: LoginUseCase,
    val signUpUseCase: SignUpUseCase,
    val saveSessionUseCase: SaveSessionUseCase,
    val saveRoleNameUseCase: SaveRoleNameUseCase,
    val getSessionDataUseCase: GetSessionDataUseCase,
    val getRoleNameUseCase: GetRoleNameUseCase,
    val logOutUseCase: LogOutUseCase,
    val updateSessionUseCase: UpdateSessionUseCase
)