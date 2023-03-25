package com.edival.reciostore.presentation.screens.client.category.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.edival.reciostore.presentation.screens.client.category.list.components.ClientCategoryListContent

@Composable
fun ClientCategoryListScreen() {
    Scaffold(content = { padding -> ClientCategoryListContent(padding) })
}