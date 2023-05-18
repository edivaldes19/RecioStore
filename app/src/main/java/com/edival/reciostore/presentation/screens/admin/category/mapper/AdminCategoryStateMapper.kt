package com.edival.reciostore.presentation.screens.admin.category.mapper

import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.presentation.screens.admin.category.AdminCategoryState

fun AdminCategoryState.toCategory(): Category {
    return Category(name = name.trim(), description = description.trim(), img = img)
}