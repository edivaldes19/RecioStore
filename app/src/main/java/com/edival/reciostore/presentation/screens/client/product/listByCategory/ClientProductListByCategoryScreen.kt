package com.edival.reciostore.presentation.screens.client.product.listByCategory

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.domain.model.Category
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.client.product.listByCategory.components.GetProductsByCategoryClient

@Composable
fun ClientProductListByCategoryScreen(navHostController: NavHostController, ctgParam: String) {
    Scaffold(topBar = {
        DefaultTopBar(
            titleStr = Category.fromJson(ctgParam).name,
            upAvailable = true,
            navHostController = navHostController
        )
    }) { padding -> GetProductsByCategoryClient(navHostController, padding) }
}