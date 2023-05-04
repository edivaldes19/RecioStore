package com.edival.reciostore.presentation.screens.client.product.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.client.product.list.components.GetProducts

@Composable
fun ClientProductListScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(
            titleRes = R.string.products, hasActions = true, navHostController = navHostController
        )
    }) { padding -> GetProducts(navHostController, padding) }
}