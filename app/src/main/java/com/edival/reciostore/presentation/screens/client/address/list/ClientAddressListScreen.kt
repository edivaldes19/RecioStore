package com.edival.reciostore.presentation.screens.client.address.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultButton
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.navigation.screen.client.ShoppingBagScreen
import com.edival.reciostore.presentation.screens.client.address.list.components.CreateOrder
import com.edival.reciostore.presentation.screens.client.address.list.components.DeleteAddress
import com.edival.reciostore.presentation.screens.client.address.list.components.GetAddress

@Composable
fun ClientAddressListScreen(
    navHostController: NavHostController, vm: ClientAddressListViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        DefaultTopBar(titleRes = R.string.my_addresses, navHostController = navHostController)
    }, floatingActionButton = {
        FloatingActionButton(onClick = { navHostController.navigate(ShoppingBagScreen.AddressCreate.route) }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White)
        }
    }, bottomBar = {
        DefaultButton(modifier = Modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(R.dimen.padding_default)),
            text = R.string.confirm_order,
            onClick = { vm.createOrder() })
    }) { padding -> GetAddress(navHostController, padding) }
    DeleteAddress()
    CreateOrder()
}