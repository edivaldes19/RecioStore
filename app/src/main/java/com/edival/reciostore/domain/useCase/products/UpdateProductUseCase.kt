package com.edival.reciostore.domain.useCase.products

import android.net.Uri
import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.domain.repository.ProductsRepository
import com.edival.reciostore.domain.util.Resource

class UpdateProductUseCase(private val repository: ProductsRepository) {
    suspend operator fun invoke(
        id: String, product: Product, images: List<Uri>?
    ): Resource<Product> = repository.updateProduct(id, product, images)
}