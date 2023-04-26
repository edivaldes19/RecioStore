package com.edival.reciostore.domain.useCase.products

data class ProductsUseCase(
    val getProductsUseCase: GetProductsUseCase,
    val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
    val createProductUseCase: CreateProductUseCase,
    val updateProductUseCase: UpdateProductUseCase,
    val updateProductImagesUseCase: UpdateProductImagesUseCase,
    val deleteProductUseCase: DeleteProductUseCase
)