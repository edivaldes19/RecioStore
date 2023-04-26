package com.edival.reciostore.domain.useCase.products

import com.edival.reciostore.domain.repository.ProductsRepository
import com.edival.reciostore.domain.util.Resource

class DeleteProductUseCase(private val repository: ProductsRepository) {
    suspend operator fun invoke(id: String): Resource<Unit> = repository.deleteProduct(id)
}