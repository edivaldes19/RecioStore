package com.edival.reciostore.domain.useCase.products

import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.domain.repository.ProductsRepository
import com.edival.reciostore.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetProductsByNameUseCase(private val repository: ProductsRepository) {
    operator fun invoke(name: String): Flow<Resource<List<Product>>> {
        return repository.getProductsByName(name)
    }
}