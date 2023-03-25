package com.edival.reciostore.presentation.navigation.graph.client

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.navigation.graph.profile.profileNavGraph
import com.edival.reciostore.presentation.navigation.screen.client.ClientScreen
import com.edival.reciostore.presentation.screens.client.category.list.ClientCategoryListScreen
import com.edival.reciostore.presentation.screens.client.product.list.ClientProductListScreen
import com.edival.reciostore.presentation.screens.profile.info.ProfileScreen

@Composable
fun ClientNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.CLIENT,
        startDestination = ClientScreen.ProductList.route
    ) {
        composable(ClientScreen.ProductList.route) { ClientProductListScreen() }
        composable(ClientScreen.CategoryList.route) { ClientCategoryListScreen() }
        composable(ClientScreen.Profile.route) { ProfileScreen(navHostController) }
        profileNavGraph(navHostController)
    }
}