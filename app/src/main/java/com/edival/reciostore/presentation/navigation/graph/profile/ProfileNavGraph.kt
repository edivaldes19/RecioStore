package com.edival.reciostore.presentation.navigation.graph.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.navigation.screen.profile.ProfileScreen
import com.edival.reciostore.presentation.screens.profile.change_password.ProfileChangePasswordScreen
import com.edival.reciostore.presentation.screens.profile.role_assignment.ProfileRoleAssignmentScreen
import com.edival.reciostore.presentation.screens.profile.update.ProfileUpdateScreen

fun NavGraphBuilder.profileNavGraph(navHostController: NavHostController) {
    navigation(route = Graph.PROFILE, startDestination = ProfileScreen.ProfileUpdate.route) {
        composable(ProfileScreen.ProfileUpdate.route) { ProfileUpdateScreen(navHostController) }
        composable(
            route = ProfileScreen.ProfileRoleFunctions.route,
            arguments = listOf(navArgument("role_assignment") { type = NavType.StringType })
        ) { entry ->
            entry.arguments?.getString("role_assignment")?.let {
                ProfileRoleAssignmentScreen(navHostController)
            }
        }
        composable(
            route = ProfileScreen.ProfileChangePassword.route,
            arguments = listOf(navArgument("id_user") { type = NavType.StringType })
        ) { entry ->
            entry.arguments?.getString("id_user")?.let {
                ProfileChangePasswordScreen(navHostController)
            }
        }
    }
}