package com.edival.reciostore.presentation.screens.admin.product.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.edival.reciostore.presentation.screens.admin.product.list.components.AdminProductListContent

@Composable
fun AdminProductListScreen() {
    Scaffold(content = { padding -> AdminProductListContent(padding) })
}