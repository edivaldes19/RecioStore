package com.edival.reciostore.presentation.screens.admin.order.detail

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.admin.order.detail.components.AdminOrderDetailContent
import com.edival.reciostore.presentation.screens.admin.order.detail.components.UpdateOrderStatus

@Composable
fun AdminOrderDetailScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(titleRes = R.string.order_detail, navHostController = navHostController)
    }) { padding -> AdminOrderDetailContent(padding) }
    UpdateOrderStatus()
}