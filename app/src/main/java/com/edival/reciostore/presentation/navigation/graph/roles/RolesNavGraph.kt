package com.edival.reciostore.presentation.navigation.graph.roles

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.navigation.screen.roles.RolesScreen
import com.edival.reciostore.presentation.screens.admin.home.AdminHomeScreen
import com.edival.reciostore.presentation.screens.client.home.ClientHomeScreen
import com.edival.reciostore.presentation.screens.roles.RolesScreen

fun NavGraphBuilder.rolesNavGraph(navHostController: NavHostController) {
    navigation(route = Graph.ROLES, startDestination = RolesScreen.Roles.route) {
        composable(route = RolesScreen.Roles.route) { RolesScreen(navHostController) }
        composable(route = Graph.ADMIN) { AdminHomeScreen() }
        composable(route = Graph.CLIENT) { ClientHomeScreen() }
    }
}