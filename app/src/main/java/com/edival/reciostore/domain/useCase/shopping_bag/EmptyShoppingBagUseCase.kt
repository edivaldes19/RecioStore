package com.edival.reciostore.domain.useCase.shopping_bag

import com.edival.reciostore.domain.repository.ShoppingBagRepository

class EmptyShoppingBagUseCase(private val repository: ShoppingBagRepository) {
    suspend operator fun invoke() = repository.emptyShoppingBag()
}