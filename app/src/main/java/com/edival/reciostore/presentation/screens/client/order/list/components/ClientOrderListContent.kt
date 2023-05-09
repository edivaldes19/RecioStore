package com.edival.reciostore.presentation.screens.client.order.list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            .padding(padding)
            .padding(bottom = 55.dp)
    ) {
        items(items = orders) { order ->
            OrderListItem(order) {
                navHostController.navigate(ClientOrderScreen.OrderDetail.passOrder(order.toJson()))
            }
        }
    }
}