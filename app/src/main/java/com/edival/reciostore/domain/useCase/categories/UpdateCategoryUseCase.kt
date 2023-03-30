package com.edival.reciostore.domain.useCase.categories

import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.domain.repository.CategoriesRepository
import com.edival.reciostore.domain.util.Resource

class UpdateCategoryUseCase(private val categoriesRepository: CategoriesRepository) {
    suspend operator fun invoke(id: String, category: Category): Resource<Category> {
        return categoriesRepository.updateCategory(id, category)
    }
}