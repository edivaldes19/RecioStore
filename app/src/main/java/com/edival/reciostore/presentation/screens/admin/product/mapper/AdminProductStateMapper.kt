package com.edival.reciostore.presentation.screens.admin.product.mapper

import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.presentation.screens.admin.product.AdminProductState

fun AdminProductState.toProduct(): Product {
    return Product(
        name = name,
        description = description,
        price = price,
        id_category = id_category,
        img1 = img1,
        img2 = img2,
        images_to_update = images_to_update
    )
}