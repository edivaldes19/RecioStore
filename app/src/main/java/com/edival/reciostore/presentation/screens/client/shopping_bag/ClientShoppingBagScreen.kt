package com.edival.reciostore.presentation.screens.client.shopping_bag

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.client.shopping_bag.components.ClientShoppingBagBottomBar
import com.edival.reciostore.presentation.screens.client.shopping_bag.components.ClientShoppingBagContent

@Composable
fun ClientShoppingBagScreen(
    navHostController: NavHostController, vm: ClientShoppingBagViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        DefaultTopBar(
            titleRes = R.string.my_order, upAvailable = true, navHostController = navHostController
        )
    }, bottomBar = { ClientShoppingBagBottomBar(navHostController) }) { padding ->
        ClientShoppingBagContent(padding, vm.shoppingBag)
    }
}