package com.edival.reciostore.presentation.screens.client.product.list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.edival.reciostore.domain.model.Product

@Composable
fun ClientProductListContent(
    navHostController: NavHostController, padding: PaddingValues, products: List<Product>
) {
    LazyColumn(
        modifier = Modifier
            .padding(padding)
            .padding(bottom = 55.dp)
    ) {
        items(items = products) { product -> ClientProductListItem(navHostController, product) }
    }
}