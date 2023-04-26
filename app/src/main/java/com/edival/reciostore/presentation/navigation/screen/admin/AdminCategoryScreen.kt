package com.edival.reciostore.presentation.navigation.screen.admin

sealed class AdminCategoryScreen(val route: String) {
    object CategoryCreate : AdminCategoryScreen("admin/category/create")
    object CategoryUpdate : AdminCategoryScreen("admin/category/update/{category}") {
        fun passCategory(category: String): String = "admin/category/update/$category"
    }

    object ProductList : AdminCategoryScreen("admin/category/products/list/{category}") {
        fun passCategory(category: String): String = "admin/category/products/list/$category"
    }

    object ProductCreate : AdminCategoryScreen("admin/category/products/create/{category}") {
        fun passCategory(category: String): String = "admin/category/products/create/$category"
    }

    object ProductUpdate : AdminCategoryScreen("admin/category/products/update/{product}") {
        fun passProduct(product: String): String = "admin/category/products/update/$product"
    }
}