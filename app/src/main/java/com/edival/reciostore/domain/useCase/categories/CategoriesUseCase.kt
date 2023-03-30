package com.edival.reciostore.domain.useCase.categories

data class CategoriesUseCase(
    val createCategoryUseCase: CreateCategoryUseCase,
    val getCategoriesUseCase: GetCategoriesUseCase,
    val updateCategoryUseCase: UpdateCategoryUseCase,
    val updateCategoryImageUseCase: UpdateCategoryImageUseCase,
    val deleteCategoryUseCase: DeleteCategoryUseCase
)