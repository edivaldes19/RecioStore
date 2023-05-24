package com.edival.reciostore.domain.useCase.products

import android.content.Context
import com.edival.reciostore.domain.repository.ProductsRepository
import com.edival.reciostore.domain.util.Resource

class DownloadProdImagesUseCase(private val productsRepository: ProductsRepository) {
    suspend operator fun invoke(
        ctx: Context, prodName: String, urls: List<String>
    ): Resource<Unit> = productsRepository.downloadProdImages(ctx, prodName, urls)
}