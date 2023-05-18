package com.edival.reciostore.presentation.navigation.graph.admin

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.navigation.screen.admin.AdminUserScreen
import com.edival.reciostore.presentation.screens.admin.user.detail.AdminUserDetailScreen

fun NavGraphBuilder.adminUserNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.ADMIN_USER, startDestination = AdminUserScreen.UserDetail.route
    ) {
        composable(
            route = AdminUserScreen.UserDetail.route, arguments = listOf(navArgument("user") {
                type = NavType.StringType
            })
        ) { entry ->
            entry.arguments?.getString("user")?.let { AdminUserDetailScreen(navHostController) }
        }
    }
}