package com.edival.reciostore.presentation.screens.admin.product.mapper

import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.presentation.screens.admin.product.AdminProductState

fun AdminProductState.toProduct(): Product {
    return Product(
        name = name.trim(),
        description = description.trim(),
        price = price,
        id_category = id_category.trim(),
        phi = phi
    )
}