package com.edival.reciostore.presentation.navigation.screen.client

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.edival.reciostore.R

sealed class ClientScreen(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    object CategoryList : ClientScreen(
        route = "client/category/list", title = R.string.categories, icon = Icons.Outlined.List
    )

    object ProductList : ClientScreen(
        route = "client/product/list", title = R.string.products, icon = Icons.Default.List
    )

    object OrderList : ClientScreen(
        route = "client/order/list", title = R.string.orders_src, icon = Icons.Default.ShoppingCart
    )

    object Profile : ClientScreen(
        route = "client/profile", title = R.string.profile, icon = Icons.Outlined.Person
    )
}