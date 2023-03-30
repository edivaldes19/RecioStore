package com.edival.reciostore.di

import com.edival.reciostore.domain.repository.AuthRepository
import com.edival.reciostore.domain.repository.CategoriesRepository
import com.edival.reciostore.domain.repository.UsersRepository
import com.edival.reciostore.domain.useCase.auth.*
import com.edival.reciostore.domain.useCase.categories.*
import com.edival.reciostore.domain.useCase.users.UpdateUserDataUseCase
import com.edival.reciostore.domain.useCase.users.UpdateUserImageUseCase
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
            logOutUseCase = LogOutUseCase(authRepository),
            updateSessionUseCase = UpdateSessionUseCase(authRepository)
        )
    }

    @Provides
    fun provideUsersUseCase(usersRepository: UsersRepository): UsersUseCase {
        return UsersUseCase(
            updateUserDataUseCase = UpdateUserDataUseCase(usersRepository),
            updateUserImageUseCase = UpdateUserImageUseCase(usersRepository)
        )
    }

    @Provides
    fun provideCategoriesUseCase(categoriesRepository: CategoriesRepository): CategoriesUseCase {
        return CategoriesUseCase(
            createCategoryUseCase = CreateCategoryUseCase(categoriesRepository),
            getCategoriesUseCase = GetCategoriesUseCase(categoriesRepository),
            updateCategoryUseCase = UpdateCategoryUseCase(categoriesRepository),
            updateCategoryImageUseCase = UpdateCategoryImageUseCase(categoriesRepository),
            deleteCategoryUseCase = DeleteCategoryUseCase(categoriesRepository)
        )
    }
}