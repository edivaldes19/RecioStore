package com.edival.reciostore.presentation.navigation.screen.admin

sealed class AdminOrderScreen(val route: String) {
    object OrderDetail : AdminOrderScreen("admin/order/detail/{order}") {
        fun passOrder(order: String) = "admin/order/detail/$order"
    }
}