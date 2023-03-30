package com.edival.reciostore.domain.useCase.categories

import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.domain.repository.CategoriesRepository
import com.edival.reciostore.domain.util.Resource
import java.io.File

class UpdateCategoryImageUseCase(private val categoriesRepository: CategoriesRepository) {
    suspend operator fun invoke(id: String, file: File): Resource<Category> {
        return categoriesRepository.updateCategoryImage(id, file)
    }
}