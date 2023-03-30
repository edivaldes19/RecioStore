package com.edival.reciostore.data.mapper

import com.edival.reciostore.data.dataSource.local.entity.CategoryEntity
import com.edival.reciostore.domain.model.Category

fun CategoryEntity.toCategory(): Category {
    return Category(id = id, name = name, description = description, img = img)
}

fun Category.toCategoryEntity(): CategoryEntity {
    return CategoryEntity(
        id = id.orEmpty(),
        name = name.orEmpty(),
        description = description.orEmpty(),
        img = img.orEmpty()
    )
}