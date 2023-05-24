package com.edival.reciostore.presentation.screens.admin.product

import com.edival.reciostore.domain.model.ProductHasImages

data class AdminProductState(
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val id_category: String = "",
    val phi: List<ProductHasImages>? = null
)