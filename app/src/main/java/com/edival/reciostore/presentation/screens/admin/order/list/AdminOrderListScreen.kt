package com.edival.reciostore.presentation.screens.admin.order.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.presentation.screens.admin.order.list.components.GetOrders

@Composable
fun AdminOrderListScreen(navHostController: NavHostController) {
    Scaffold { padding -> GetOrders(navHostController, padding) }
}