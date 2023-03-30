package com.edival.reciostore.presentation.navigation.graph.admin

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.navigation.screen.admin.AdminCategoryScreen
import com.edival.reciostore.presentation.screens.admin.category.create.AdminCategoryCreateScreen
import com.edival.reciostore.presentation.screens.admin.category.update.AdminCategoryUpdateScreen

fun NavGraphBuilder.adminCategoryNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.ADMIN_CATEGORY, startDestination = AdminCategoryScreen.CategoryCreate.route
    ) {
        composable(route = AdminCategoryScreen.CategoryCreate.route) {
            AdminCategoryCreateScreen(navHostController)
        }
        composable(
            route = AdminCategoryScreen.CategoryUpdate.route,
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })
        ) { entry ->
            entry.arguments?.getString("category")?.let {
                AdminCategoryUpdateScreen(navHostController)
            }
        }
    }
}