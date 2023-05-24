package com.edival.reciostore.domain.useCase.auth

data class AuthUseCase(
    val createTokenUseCase: CreateTokenUseCase,
    val signUpUseCase: SignUpUseCase,
    val loginUseCase: LoginUseCase,
    val updatePasswordUseCase: UpdatePasswordUseCase,
    val updateNotificationTokenUseCase: UpdateNotificationTokenUseCase,
    val deleteAccountUseCase: DeleteAccountUseCase,
    val saveSessionUseCase: SaveSessionUseCase,
    val getSessionDataUseCase: GetSessionDataUseCase,
    val logOutUseCase: LogOutUseCase,
    val updateSessionUseCase: UpdateSessionUseCase
)