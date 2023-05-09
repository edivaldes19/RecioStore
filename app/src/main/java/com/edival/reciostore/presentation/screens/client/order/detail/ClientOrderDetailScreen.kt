package com.edival.reciostore.presentation.screens.client.order.detail

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.client.order.detail.components.ClientOrderDetailContent

@Composable
fun ClientOrderDetailScreen(
    navHostController: NavHostController, vm: ClientOrderDetailViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        DefaultTopBar(
            titleRes = R.string.order_detail,
            navHostController = navHostController,
            upAvailable = true
        )
    }) { padding -> vm.order?.let { ord -> ClientOrderDetailContent(padding, ord) } }
}