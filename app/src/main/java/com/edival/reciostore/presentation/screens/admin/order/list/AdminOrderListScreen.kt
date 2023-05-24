package com.edival.reciostore.presentation.screens.admin.order.list

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.MainTopBar
import com.edival.reciostore.presentation.screens.admin.order.list.components.GetOrders
import kotlinx.coroutines.CoroutineScope

@Composable
fun AdminOrderListScreen(
    navHostController: NavHostController, scope: CoroutineScope, scaffoldState: ScaffoldState
) {
    Scaffold(scaffoldState = scaffoldState,
        topBar = { MainTopBar(R.string.orders_src, scope, scaffoldState) }) { padding ->
        GetOrders(navHostController, padding)
    }
}