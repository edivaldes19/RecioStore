package com.edival.reciostore.presentation.screens.admin.category

import android.net.Uri

data class AdminCategoryState(
    val name: String = "",
    val description: String = "",
    val img: String? = null,
    val imgSelected: Uri? = null
)