package com.edival.reciostore.domain.useCase.shopping_bag

import com.edival.reciostore.domain.model.ShoppingBagProduct
import com.edival.reciostore.domain.repository.ShoppingBagRepository

class GetProductBagByIdUseCase(private val repository: ShoppingBagRepository) {
    suspend operator fun invoke(id: String): ShoppingBagProduct? = repository.getProductBagById(id)
}