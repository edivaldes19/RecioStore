package com.edival.reciostore.presentation.navigation.screen.client

sealed class ClientOrderScreen(val route: String) {
    object OrderDetail : ClientOrderScreen("client/order/detail/{order}") {
        fun passOrder(order: String) = "client/order/detail/$order"
    }
}