package com.edival.reciostore.domain.useCase.categories

import com.edival.reciostore.domain.repository.CategoriesRepository
import com.edival.reciostore.domain.util.Resource

class DeleteCategoryUseCase(private val categoriesRepository: CategoriesRepository) {
    suspend operator fun invoke(id: String): Resource<Unit> {
        return categoriesRepository.deleteCategory(id)
    }
}