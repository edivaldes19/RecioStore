package com.edival.reciostore.domain.useCase.products

import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.domain.repository.ProductsRepository
import com.edival.reciostore.domain.util.Resource
import java.io.File

class UpdateProductImagesUseCase(private val repository: ProductsRepository) {
    suspend operator fun invoke(
        files: List<File>, id: String, product: Product
    ): Resource<Product> = repository.updateProductImages(files, id, product)
}