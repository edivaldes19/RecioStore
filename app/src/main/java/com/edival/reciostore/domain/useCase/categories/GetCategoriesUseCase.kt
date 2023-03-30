package com.edival.reciostore.domain.useCase.categories

import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.domain.repository.CategoriesRepository
import com.edival.reciostore.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase(private val categoriesRepository: CategoriesRepository) {
    operator fun invoke(): Flow<Resource<List<Category>>> {
        return categoriesRepository.getCategories()
    }
}