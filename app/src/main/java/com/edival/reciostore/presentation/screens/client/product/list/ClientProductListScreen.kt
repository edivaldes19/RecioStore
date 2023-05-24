package com.edival.reciostore.presentation.screens.client.product.list

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.MainTopBar
import com.edival.reciostore.presentation.screens.client.product.list.components.GetProducts
import kotlinx.coroutines.CoroutineScope

@Composable
fun ClientProductListScreen(
    navHostController: NavHostController, scope: CoroutineScope, scaffoldState: ScaffoldState
) {
    Scaffold(scaffoldState = scaffoldState,
        topBar = { MainTopBar(R.string.products, scope, scaffoldState) }) { padding ->
        GetProducts(navHostController, padding)
    }
}
//        DefaultSearchView(
//            value = vm.search,
//            onValueChange = { query -> vm.onSearchInput(query) },
//            navHostController = navHostController
//        ) { if (vm.search.isNotBlank()) vm.getProductsByName(vm.search) }