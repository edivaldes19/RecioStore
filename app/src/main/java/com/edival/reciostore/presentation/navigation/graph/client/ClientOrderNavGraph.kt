package com.edival.reciostore.presentation.navigation.graph.client

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.navigation.screen.client.ClientOrderScreen
import com.edival.reciostore.presentation.screens.client.order.detail.ClientOrderDetailScreen

fun NavGraphBuilder.clientOrderNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.CLIENT_ORDER, startDestination = ClientOrderScreen.OrderDetail.route
    ) {
        composable(
            route = ClientOrderScreen.OrderDetail.route, arguments = listOf(navArgument("order") {
                type = NavType.StringType
            })
        ) { entry ->
            entry.arguments?.getString("order")?.let {
                ClientOrderDetailScreen(navHostController)
            }
        }
    }
}