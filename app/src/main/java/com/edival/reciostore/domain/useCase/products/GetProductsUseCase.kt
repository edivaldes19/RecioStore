package com.edival.reciostore.domain.useCase.products

import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.domain.repository.ProductsRepository
import com.edival.reciostore.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(private val repository: ProductsRepository) {
    operator fun invoke(): Flow<Resource<List<Product>>> = repository.getProducts()
}