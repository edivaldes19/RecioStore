package com.edival.reciostore.presentation.navigation.screen.admin

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.edival.reciostore.R

sealed class AdminScreen(val route: String, @StringRes val title: Int, @DrawableRes val icon: Int) {
    object CategoryList : AdminScreen(
        route = "admin/category/list",
        title = R.string.categories,
        icon = R.drawable.outline_category
    )

    object OrderList : AdminScreen(
        route = "admin/order/list",
        title = R.string.orders_src,
        icon = R.drawable.outline_shopping_cart
    )

    object UserList : AdminScreen(
        route = "admin/user/list", title = R.string.users_src, icon = R.drawable.outline_groups
    )

    object Profile : AdminScreen(
        route = "admin/profile", title = R.string.profile, icon = R.drawable.outline_person
    )
}