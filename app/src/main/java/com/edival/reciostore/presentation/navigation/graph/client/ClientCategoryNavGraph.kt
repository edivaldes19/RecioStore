package com.edival.reciostore.presentation.navigation.graph.client

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.navigation.screen.client.ClientCategoryScreen
import com.edival.reciostore.presentation.screens.client.product.detail.ClientProductDetailScreen
import com.edival.reciostore.presentation.screens.client.product.listByCategory.ClientProductListByCategoryScreen

fun NavGraphBuilder.clientCategoryNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.CLIENT_CATEGORY, startDestination = ClientCategoryScreen.ProductList.route
    ) {
        composable(
            route = ClientCategoryScreen.ProductList.route,
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })
        ) { entry ->
            entry.arguments?.getString("category")?.let { ctgParam ->
                ClientProductListByCategoryScreen(navHostController, ctgParam)
            }
        }
        composable(
            route = ClientCategoryScreen.ProductDetail.route,
            arguments = listOf(navArgument("product") { type = NavType.StringType })
        ) { entry ->
            entry.arguments?.getString("product")?.let { ClientProductDetailScreen() }
        }
    }
}