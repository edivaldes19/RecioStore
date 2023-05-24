package com.edival.reciostore.presentation.screens.client.product.detail

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.client.product.detail.components.ClientProductDetailContent

@Composable
fun ClientProductDetailScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(titleRes = R.string.product_detail, navHostController = navHostController)
    }) { padding -> ClientProductDetailContent(padding) }
}