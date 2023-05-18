package com.edival.reciostore.presentation.navigation.screen.client

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.edival.reciostore.R

sealed class ClientScreen(
    val route: String, @StringRes val title: Int, @DrawableRes val icon: Int
) {
    object CategoryList : ClientScreen(
        route = "client/category/list",
        title = R.string.categories,
        icon = R.drawable.outline_category
    )

    object ProductList : ClientScreen(
        route = "client/product/list", title = R.string.products, icon = R.drawable.outline_list
    )

    object OrderList : ClientScreen(
        route = "client/order/list",
        title = R.string.orders_src,
        icon = R.drawable.outline_shopping_cart
    )

    object Profile : ClientScreen(
        route = "client/profile", title = R.string.profile, icon = R.drawable.outline_person
    )
}