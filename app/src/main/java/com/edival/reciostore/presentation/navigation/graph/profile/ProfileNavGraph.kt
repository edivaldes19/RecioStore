package com.edival.reciostore.presentation.navigation.graph.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.navigation.screen.profile.ProfileScreen
import com.edival.reciostore.presentation.screens.profile.update.ProfileUpdateScreen

fun NavGraphBuilder.profileNavGraph(navHostController: NavHostController) {
    navigation(
        route = "${Graph.PROFILE}/{user}", startDestination = ProfileScreen.ProfileUpdate.route
    ) {
        composable(
            route = ProfileScreen.ProfileUpdate.route, arguments = listOf(navArgument("user") {
                type = NavType.StringType
            })
        ) { entry ->
            entry.arguments?.getString("user")?.let { ProfileUpdateScreen(navHostController) }
        }
    }
}