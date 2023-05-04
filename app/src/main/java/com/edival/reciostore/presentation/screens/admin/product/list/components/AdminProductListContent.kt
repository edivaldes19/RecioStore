package com.edival.reciostore.presentation.screens.admin.product.list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.edival.reciostore.domain.model.Product

@Composable
fun AdminProductListContent(
    navHostController: NavHostController, padding: PaddingValues, products: List<Product>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        items(items = products) { product -> AdminProductListItem(navHostController, product) }
    }
}