package com.edival.reciostore.presentation.screens.client.order.list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.edival.reciostore.domain.model.Order
import com.edival.reciostore.presentation.components.OrderListItem
import com.edival.reciostore.presentation.navigation.screen.client.ClientOrderScreen

@Composable
fun ClientOrderListContent(
    padding: PaddingValues, orders: List<Order>, navHostController: NavHostController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        items(items = orders) { order ->
            order.user?.password = null
            OrderListItem(order) {
                navHostController.navigate(ClientOrderScreen.OrderDetail.passOrder(order.toJson()))
            }
        }
    }
}