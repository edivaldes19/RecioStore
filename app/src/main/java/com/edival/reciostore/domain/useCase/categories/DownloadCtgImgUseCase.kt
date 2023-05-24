package com.edival.reciostore.domain.useCase.categories

import android.content.Context
import com.edival.reciostore.domain.repository.CategoriesRepository
import com.edival.reciostore.domain.util.Resource

class DownloadCtgImgUseCase(private val categoriesRepository: CategoriesRepository) {
    suspend operator fun invoke(ctx: Context, url: String): Resource<Unit> {
        return categoriesRepository.downloadCtgImg(ctx, url)
    }
}