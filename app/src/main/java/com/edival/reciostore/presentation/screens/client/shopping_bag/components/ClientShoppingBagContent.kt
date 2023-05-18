package com.edival.reciostore.presentation.screens.client.shopping_bag.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.edival.reciostore.domain.model.ShoppingBagProduct

@Composable
fun ClientShoppingBagContent(padding: PaddingValues, products: List<ShoppingBagProduct>) {
    LazyColumn(modifier = Modifier.padding(padding)) {
        items(items = products) { product -> ClientShoppingBagItem(product) }
    }
}