package com.edival.reciostore.presentation.navigation.graph.admin

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.navigation.graph.profile.profileNavGraph
import com.edival.reciostore.presentation.navigation.screen.admin.AdminScreen
import com.edival.reciostore.presentation.screens.admin.category.list.AdminCategoryListScreen
import com.edival.reciostore.presentation.screens.admin.product.list.AdminProductListScreen
import com.edival.reciostore.presentation.screens.profile.info.ProfileScreen

@Composable
fun AdminNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.ADMIN,
        startDestination = AdminScreen.ProductList.route
    ) {
        composable(AdminScreen.ProductList.route) { AdminProductListScreen() }
        composable(AdminScreen.CategoryList.route) { AdminCategoryListScreen() }
        composable(AdminScreen.Profile.route) { ProfileScreen(navHostController) }
        profileNavGraph(navHostController)
    }
}