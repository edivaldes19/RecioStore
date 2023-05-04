package com.edival.reciostore.presentation.screens.admin.product.create

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.admin.product.create.components.AdminProductCreateContent
import com.edival.reciostore.presentation.screens.admin.product.create.components.CreateProduct

@Composable
fun AdminProductCreateScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(
            titleRes = R.string.create_product,
            upAvailable = true,
            navHostController = navHostController
        )
    }) { padding -> AdminProductCreateContent(padding) }
    CreateProduct()
}