package com.edival.reciostore.presentation.screens.client.product.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.edival.reciostore.presentation.screens.client.product.list.components.ClientProductListContent

@Composable
fun ClientProductListScreen() {
    Scaffold(content = { padding -> ClientProductListContent(padding) })
}