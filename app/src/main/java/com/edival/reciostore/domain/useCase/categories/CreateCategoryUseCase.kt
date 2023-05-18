package com.edival.reciostore.domain.useCase.categories

import android.net.Uri
import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.domain.repository.CategoriesRepository
import com.edival.reciostore.domain.util.Resource

class CreateCategoryUseCase(private val categoriesRepository: CategoriesRepository) {
    suspend operator fun invoke(category: Category, uri: Uri): Resource<Category> {
        return categoriesRepository.createCategory(category, uri)
    }
}