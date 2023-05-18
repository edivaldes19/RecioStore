package com.edival.reciostore.presentation.navigation.screen.client

sealed class ClientProductScreen(val route: String) {
    object ProductDetail : ClientProductScreen("client/products/detail/{product}") {
        fun passProduct(product: String): String = "client/products/detail/$product"
    }
}