package com.edival.reciostore.presentation.screens.client.address.create

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.client.address.create.components.ClientAddressCreateContent
import com.edival.reciostore.presentation.screens.client.address.create.components.CreateAddress

@Composable
fun ClientAddressCreateScreen(
    navHostController: NavHostController, vm: ClientAddressCreateViewModel = hiltViewModel()
) {
    vm.getSessionData()
    Scaffold(
        topBar = {
            DefaultTopBar(
                titleRes = R.string.new_address,
                navHostController = navHostController,
                upAvailable = true
            )
        },
    ) { paddingValues -> ClientAddressCreateContent(paddingValues) }
    CreateAddress()
}