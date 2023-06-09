package com.edival.reciostore.presentation.screens.client.product.listByCategory.components

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
fun ClientProductListByCategoryContent(
    navHostController: NavHostController, padding: PaddingValues, products: List<Product>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        items(items = products) { prod -> ClientProductListByCategoryItem(navHostController, prod) }
    }
}