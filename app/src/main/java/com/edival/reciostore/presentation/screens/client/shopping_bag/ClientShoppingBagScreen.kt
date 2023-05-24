package com.edival.reciostore.presentation.screens.client.shopping_bag

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.MainTopBar
import com.edival.reciostore.presentation.screens.client.shopping_bag.components.ClientShoppingBagBottomBar
import com.edival.reciostore.presentation.screens.client.shopping_bag.components.ClientShoppingBagContent
import kotlinx.coroutines.CoroutineScope

@Composable
fun ClientShoppingBagScreen(
    navHostController: NavHostController, scope: CoroutineScope, scaffoldState: ScaffoldState
) {
    Scaffold(topBar = { MainTopBar(R.string.shopping_bag, scope, scaffoldState) },
        bottomBar = { ClientShoppingBagBottomBar(navHostController) }) { padding ->
        ClientShoppingBagContent(padding)
    }
}