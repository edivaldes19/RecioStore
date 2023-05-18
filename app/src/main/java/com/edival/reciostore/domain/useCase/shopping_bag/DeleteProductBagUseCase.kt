package com.edival.reciostore.domain.useCase.shopping_bag

import com.edival.reciostore.domain.repository.ShoppingBagRepository

class DeleteProductBagUseCase(private val repository: ShoppingBagRepository) {
    suspend operator fun invoke(idProduct: String) = repository.deleteProductBag(idProduct)
}