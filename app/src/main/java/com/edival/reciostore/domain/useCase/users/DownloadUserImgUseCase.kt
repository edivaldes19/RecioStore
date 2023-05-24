package com.edival.reciostore.domain.useCase.users

import android.content.Context
import com.edival.reciostore.domain.repository.UsersRepository
import com.edival.reciostore.domain.util.Resource

class DownloadUserImgUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(ctx: Context, url: String): Resource<Unit> {
        return usersRepository.downloadUserImg(ctx, url)
    }
}