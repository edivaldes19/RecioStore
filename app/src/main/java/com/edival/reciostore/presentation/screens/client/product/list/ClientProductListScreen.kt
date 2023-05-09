package com.edival.reciostore.presentation.screens.client.product.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.presentation.components.DefaultSearchView
import com.edival.reciostore.presentation.screens.client.product.list.components.GetProducts

@Composable
fun ClientProductListScreen(
    navHostController: NavHostController, vm: ClientProductListViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        DefaultSearchView(
            value = vm.search,
            onValueChange = { query -> vm.onSearchInput(query) },
            navHostController = navHostController
        ) { if (vm.search.isNotBlank()) vm.getProductsByName(vm.search) }
    }) { padding -> GetProducts(navHostController, padding) }
}