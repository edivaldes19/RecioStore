package com.edival.reciostore.presentation.screens.admin.product

data class AdminProductState(
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val id_category: String = "",
    val img1: String? = null,
    val img2: String? = null
)