package com.edival.reciostore.presentation.screens.admin.product.list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AdminProductListContent(padding: PaddingValues) {
    Text(modifier = Modifier.padding(padding), text = "AdminProductListScreen")
}