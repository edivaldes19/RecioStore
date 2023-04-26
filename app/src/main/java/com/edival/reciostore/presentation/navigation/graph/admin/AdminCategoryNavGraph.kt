package com.edival.reciostore.presentation.navigation.graph.admin

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.navigation.screen.admin.AdminCategoryScreen
import com.edival.reciostore.presentation.screens.admin.category.create.AdminCategoryCreateScreen
import com.edival.reciostore.presentation.screens.admin.category.update.AdminCategoryUpdateScreen
import com.edival.reciostore.presentation.screens.admin.product.create.AdminProductCreateScreen
import com.edival.reciostore.presentation.screens.admin.product.list.AdminProductListScreen
import com.edival.reciostore.presentation.screens.admin.product.update.AdminProductUpdateScreen

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
        composable(
            route = AdminCategoryScreen.ProductList.route,
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })
        ) { entry ->
            entry.arguments?.getString("category")?.let { ctgParam ->
                AdminProductListScreen(navHostController, ctgParam)
            }
        }
        composable(
            route = AdminCategoryScreen.ProductCreate.route,
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })
        ) { entry ->
            entry.arguments?.getString("category")?.let {
                AdminProductCreateScreen(navHostController)
            }
        }
        composable(
            route = AdminCategoryScreen.ProductUpdate.route,
            arguments = listOf(navArgument("product") {
                type = NavType.StringType
            })
        ) { entry ->
            entry.arguments?.getString("product")?.let {
                AdminProductUpdateScreen(navHostController)
            }
        }
    }
}