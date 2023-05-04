package com.edival.reciostore.presentation.screens.client.address.update

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.client.address.update.components.ClientAddressUpdateContent
import com.edival.reciostore.presentation.screens.client.address.update.components.UpdateAddress

@Composable
fun ClientAddressUpdateScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(
            titleRes = R.string.update_address,
            upAvailable = true,
            navHostController = navHostController
        )
    }, content = { padding -> ClientAddressUpdateContent(padding) })
    UpdateAddress()
}