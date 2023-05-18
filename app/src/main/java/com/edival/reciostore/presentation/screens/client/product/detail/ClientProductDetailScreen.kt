package com.edival.reciostore.presentation.screens.client.product.detail

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.edival.reciostore.presentation.screens.client.product.detail.components.ClientProductDetailContent

@Composable
fun ClientProductDetailScreen() {
    Scaffold { padding -> ClientProductDetailContent(padding) }
}