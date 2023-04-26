package com.edival.reciostore.presentation.screens.client.product.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.presentation.screens.client.product.list.components.GetProducts

@Composable
fun ClientProductListScreen(navHostController: NavHostController) {
    Scaffold { padding -> GetProducts(navHostController, padding) }
}