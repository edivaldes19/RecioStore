package com.edival.reciostore.domain.useCase.auth

data class AuthUseCase(
    val loginUseCase: LoginUseCase,
    val signUpUseCase: SignUpUseCase,
    val saveSessionUseCase: SaveSessionUseCase,
    val getSessionDataUseCase: GetSessionDataUseCase,
    val logOutUseCase: LogOutUseCase,
    val updateSessionUseCase: UpdateSessionUseCase
)