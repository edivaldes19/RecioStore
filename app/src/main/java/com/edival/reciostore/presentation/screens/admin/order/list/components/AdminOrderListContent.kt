package com.edival.reciostore.presentation.screens.admin.order.list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.edival.reciostore.domain.model.Order
import com.edival.reciostore.presentation.components.OrderListItem
import com.edival.reciostore.presentation.navigation.screen.admin.AdminOrderScreen

@Composable
fun AdminOrderListContent(
    paddingValues: PaddingValues, orders: List<Order>, navHostController: NavHostController
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(bottom = 55.dp)
            .fillMaxSize()
    ) {
        items(items = orders) { order ->
            order.user?.password = null
            OrderListItem(order) {
                navHostController.navigate(AdminOrderScreen.OrderDetail.passOrder(order.toJson()))
            }
        }
    }
}