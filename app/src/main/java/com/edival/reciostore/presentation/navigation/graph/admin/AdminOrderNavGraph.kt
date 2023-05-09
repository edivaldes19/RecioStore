package com.edival.reciostore.presentation.navigation.graph.admin

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.navigation.screen.admin.AdminOrderScreen
import com.edival.reciostore.presentation.screens.admin.order.detail.AdminOrderDetailScreen

fun NavGraphBuilder.adminOrderNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.ADMIN_ORDER, startDestination = AdminOrderScreen.OrderDetail.route
    ) {
        composable(
            route = AdminOrderScreen.OrderDetail.route, arguments = listOf(navArgument("order") {
                type = NavType.StringType
            })
        ) { entry ->
            entry.arguments?.getString("order")?.let {
                AdminOrderDetailScreen(navHostController)
            }
        }
    }
}