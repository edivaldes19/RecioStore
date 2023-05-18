package com.edival.reciostore.presentation.navigation.graph.client

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.navigation.screen.client.ShoppingBagScreen
import com.edival.reciostore.presentation.screens.client.address.create.ClientAddressCreateScreen
import com.edival.reciostore.presentation.screens.client.address.list.ClientAddressListScreen
import com.edival.reciostore.presentation.screens.client.address.update.ClientAddressUpdateScreen
import com.edival.reciostore.presentation.screens.client.shopping_bag.ClientShoppingBagScreen

fun NavGraphBuilder.shoppingBagNavGraph(navHostController: NavHostController) {
    navigation(route = Graph.SHOPPING_BAG, startDestination = ShoppingBagScreen.ShoppingBag.route) {
        composable(ShoppingBagScreen.ShoppingBag.route) { ClientShoppingBagScreen(navHostController) }
        composable(ShoppingBagScreen.AddressList.route) { ClientAddressListScreen(navHostController) }
        composable(ShoppingBagScreen.AddressCreate.route) {
            ClientAddressCreateScreen(navHostController)
        }
        composable(
            route = ShoppingBagScreen.AddressUpdate.route,
            arguments = listOf(navArgument("address") { type = NavType.StringType })
        ) { entry ->
            entry.arguments?.getString("address")?.let {
                ClientAddressUpdateScreen(navHostController)
            }
        }
    }
}