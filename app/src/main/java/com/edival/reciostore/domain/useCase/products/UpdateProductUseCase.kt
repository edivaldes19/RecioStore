package com.edival.reciostore.domain.useCase.products

import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.domain.repository.ProductsRepository
import com.edival.reciostore.domain.util.Resource

class UpdateProductUseCase(private val repository: ProductsRepository) {
    suspend operator fun invoke(id: String, product: Product): Resource<Product> {
        return repository.updateProduct(id, product)
    }
}