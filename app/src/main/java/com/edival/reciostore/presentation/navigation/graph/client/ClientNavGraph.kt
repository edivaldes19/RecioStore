package com.edival.reciostore.presentation.navigation.graph.client

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.navigation.graph.profile.profileNavGraph
import com.edival.reciostore.presentation.navigation.screen.client.ClientScreen
import com.edival.reciostore.presentation.screens.client.category.list.ClientCategoryListScreen
import com.edival.reciostore.presentation.screens.client.order.list.ClientOrderListScreen
import com.edival.reciostore.presentation.screens.client.product.list.ClientProductListScreen
import com.edival.reciostore.presentation.screens.client.shopping_bag.ClientShoppingBagScreen
import com.edival.reciostore.presentation.screens.profile.info.ProfileInfoScreen
import kotlinx.coroutines.CoroutineScope

@Composable
fun ClientNavGraph(
    navHostController: NavHostController, scope: CoroutineScope, scaffoldState: ScaffoldState
) {
    NavHost(
        navController = navHostController,
        route = Graph.CLIENT,
        startDestination = ClientScreen.CategoryList.route
    ) {
        composable(ClientScreen.CategoryList.route) {
            ClientCategoryListScreen(navHostController, scope, scaffoldState)
        }
        composable(ClientScreen.ProductList.route) {
            ClientProductListScreen(navHostController, scope, scaffoldState)
        }
        composable(ClientScreen.OrderList.route) {
            ClientOrderListScreen(navHostController, scope, scaffoldState)
        }
        composable(ClientScreen.Profile.route) {
            ProfileInfoScreen(navHostController, scope, scaffoldState)
        }
        composable(ClientScreen.ShoppingBag.route) {
            ClientShoppingBagScreen(navHostController, scope, scaffoldState)
        }
        clientCategoryNavGraph(navHostController)
        clientProductNavGraph(navHostController)
        clientOrderNavGraph(navHostController)
        shoppingBagNavGraph(navHostController)
        profileNavGraph(navHostController)
    }
}