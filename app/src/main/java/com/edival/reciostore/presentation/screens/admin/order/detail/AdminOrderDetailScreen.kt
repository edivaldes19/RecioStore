package com.edival.reciostore.presentation.screens.admin.order.detail

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.admin.order.detail.components.AdminOrderDetailContent
import com.edival.reciostore.presentation.screens.admin.order.detail.components.UpdateOrderStatus

@Composable
fun AdminOrderDetailScreen(
    navHostController: NavHostController, vm: AdminOrderDetailViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        DefaultTopBar(
            titleRes = R.string.order_detail,
            navHostController = navHostController,
            upAvailable = true
        )
    }) { padding -> vm.order?.let { ord -> AdminOrderDetailContent(padding, ord) } }
    UpdateOrderStatus()
}