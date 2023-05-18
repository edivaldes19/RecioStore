package com.edival.reciostore.presentation.navigation.graph.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.navigation.graph.auth.authNavGraph
import com.edival.reciostore.presentation.navigation.graph.roles.rolesNavGraph

@Composable
fun RootNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController, route = Graph.ROOT, startDestination = Graph.AUTH
    ) {
        authNavGraph(navHostController)
        rolesNavGraph(navHostController)
    }
}