package com.edival.reciostore.presentation.navigation.graph.client

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.navigation.screen.client.ClientProductScreen
import com.edival.reciostore.presentation.screens.client.product.detail.ClientProductDetailScreen

fun NavGraphBuilder.clientProductNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.CLIENT_PRODUCT, startDestination = ClientProductScreen.ProductDetail.route
    ) {
        composable(
            route = ClientProductScreen.ProductDetail.route,
            arguments = listOf(navArgument("product") { type = NavType.StringType })
        ) { entry ->
            entry.arguments?.getString("product")?.let {
                ClientProductDetailScreen(navHostController)
            }
        }
    }
}