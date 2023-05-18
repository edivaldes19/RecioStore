package com.edival.reciostore.domain.useCase.auth

data class AuthUseCase(
    val signUpUseCase: SignUpUseCase,
    val loginUseCase: LoginUseCase,
    val updatePasswordUseCase: UpdatePasswordUseCase,
    val deleteAccountUseCase: DeleteAccountUseCase,
    val saveSessionUseCase: SaveSessionUseCase,
    val saveRoleNameUseCase: SaveRoleNameUseCase,
    val getSessionDataUseCase: GetSessionDataUseCase,
    val getRoleNameUseCase: GetRoleNameUseCase,
    val logOutUseCase: LogOutUseCase,
    val updateSessionUseCase: UpdateSessionUseCase
)