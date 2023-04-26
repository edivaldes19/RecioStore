package com.edival.reciostore.presentation.navigation.screen.client

sealed class ClientCategoryScreen(val route: String) {
    object ProductList : ClientCategoryScreen("client/category/products/list/{category}") {
        fun passCategory(category: String): String = "client/category/products/list/$category"
    }
}