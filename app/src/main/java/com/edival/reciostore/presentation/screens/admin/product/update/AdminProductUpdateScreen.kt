package com.edival.reciostore.presentation.screens.admin.product.update

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.admin.product.update.components.AdminProductUpdateContent
import com.edival.reciostore.presentation.screens.admin.product.update.components.UpdateProduct

@Composable
fun AdminProductUpdateScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(
            titleRes = R.string.update_product,
            upAvailable = true,
            navHostController = navHostController
        )
    }) { padding -> AdminProductUpdateContent(padding) }
    UpdateProduct()
}