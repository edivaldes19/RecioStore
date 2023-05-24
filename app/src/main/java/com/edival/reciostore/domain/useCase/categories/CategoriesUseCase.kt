package com.edival.reciostore.domain.useCase.categories

data class CategoriesUseCase(
    val getCategoriesUseCase: GetCategoriesUseCase,
    val createCategoryUseCase: CreateCategoryUseCase,
    val updateCategoryUseCase: UpdateCategoryUseCase,
    val deleteCategoryUseCase: DeleteCategoryUseCase,
    val downloadCtgImgUseCase: DownloadCtgImgUseCase
)