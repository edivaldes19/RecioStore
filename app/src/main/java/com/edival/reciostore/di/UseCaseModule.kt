package com.edival.reciostore.di

import com.edival.reciostore.domain.repository.AuthRepository
import com.edival.reciostore.domain.repository.UsersRepository
import com.edival.reciostore.domain.useCase.auth.*
import com.edival.reciostore.domain.useCase.users.UpdateUserDataUseCase
import com.edival.reciostore.domain.useCase.users.UsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideAuthUseCase(authRepository: AuthRepository): AuthUseCase {
        return AuthUseCase(
            loginUseCase = LoginUseCase(authRepository),
            signUpUseCase = SignUpUseCase(authRepository),
            saveSessionUseCase = SaveSessionUseCase(authRepository),
            getSessionDataUseCase = GetSessionDataUseCase(authRepository),
            logOutUseCase = LogOutUseCase(authRepository)
        )
    }

    @Provides
    fun provideUsersUseCase(usersRepository: UsersRepository): UsersUseCase {
        return UsersUseCase(updateUserDataUseCase = UpdateUserDataUseCase(usersRepository))
    }
}