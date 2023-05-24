package com.edival.reciostore.presentation.screens.client.address.create

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.client.address.create.components.ClientAddressCreateContent
import com.edival.reciostore.presentation.screens.client.address.create.components.CreateAddress

@Composable
fun ClientAddressCreateScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(titleRes = R.string.new_address, navHostController = navHostController)
    }) { padding -> ClientAddressCreateContent(padding) }
    CreateAddress(navHostController)
}