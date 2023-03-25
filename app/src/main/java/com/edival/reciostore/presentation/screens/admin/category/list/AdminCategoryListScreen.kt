package com.edival.reciostore.presentation.screens.admin.category.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.edival.reciostore.presentation.screens.admin.category.list.components.AdminCategoryListContent

@Composable
fun AdminCategoryListScreen() {
    Scaffold(content = { padding -> AdminCategoryListContent(padding) })
}