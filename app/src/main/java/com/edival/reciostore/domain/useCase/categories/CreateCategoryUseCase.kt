package com.edival.reciostore.domain.useCase.categories

import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.domain.repository.CategoriesRepository
import com.edival.reciostore.domain.util.Resource
import java.io.File

class CreateCategoryUseCase(private val categoriesRepository: CategoriesRepository) {
    suspend operator fun invoke(file: File, category: Category): Resource<Category> {
        return categoriesRepository.createCategory(file, category)
    }
}