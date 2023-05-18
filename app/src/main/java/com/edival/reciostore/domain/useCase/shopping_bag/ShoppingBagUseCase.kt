package com.edival.reciostore.domain.useCase.shopping_bag

data class ShoppingBagUseCase(
    val getProductsBagUseCase: GetProductsBagUseCase,
    val getProductBagByIdUseCase: GetProductBagByIdUseCase,
    val addProductBagUseCase: AddProductBagUseCase,
    val deleteProductBagUseCase: DeleteProductBagUseCase,
    val emptyShoppingBagUseCase: EmptyShoppingBagUseCase
)