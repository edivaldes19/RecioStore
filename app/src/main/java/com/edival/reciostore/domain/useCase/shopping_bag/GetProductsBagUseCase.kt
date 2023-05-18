package com.edival.reciostore.domain.useCase.shopping_bag

import com.edival.reciostore.domain.model.ShoppingBagProduct
import com.edival.reciostore.domain.repository.ShoppingBagRepository
import kotlinx.coroutines.flow.Flow

class GetProductsBagUseCase(private val repository: ShoppingBagRepository) {
    operator fun invoke(): Flow<List<ShoppingBagProduct>> = repository.getProductsBag()
}