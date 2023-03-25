package com.edival.reciostore.presentation.navigation.screen.admin

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.edival.reciostore.R

sealed class AdminScreen(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    object CategoryList : AdminScreen(
        route = "admin/category/list", title = R.string.categories, icon = Icons.Outlined.List
    )

    object ProductList : AdminScreen(
        route = "admin/product/list", title = R.string.products, icon = Icons.Default.List
    )

    object Profile : AdminScreen(
        route = "admin/profile", title = R.string.profile, icon = Icons.Outlined.Person
    )
}