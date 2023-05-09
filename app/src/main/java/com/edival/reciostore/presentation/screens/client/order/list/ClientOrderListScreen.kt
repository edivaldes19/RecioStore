package com.edival.reciostore.presentation.screens.client.order.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.client.order.list.components.GetOrdersByClient

@Composable
fun ClientOrderListScreen(navHostController: NavHostController) {
    Scaffold(modifier = Modifier.padding(bottom = 55.dp), topBar = {
        DefaultTopBar(titleRes = R.string.orders_src)
    }) { padding -> GetOrdersByClient(navHostController, padding) }
}