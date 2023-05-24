package com.edival.reciostore.presentation.screens.client.shopping_bag.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.presentation.screens.client.shopping_bag.ClientShoppingBagViewModel

@Composable
fun ClientShoppingBagContent(
    padding: PaddingValues, vm: ClientShoppingBagViewModel = hiltViewModel()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        items(items = vm.shoppingBag) { product -> ClientShoppingBagItem(product) }
    }
}