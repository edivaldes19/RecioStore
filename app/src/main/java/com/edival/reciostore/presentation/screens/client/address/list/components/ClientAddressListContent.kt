package com.edival.reciostore.presentation.screens.client.address.list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.edival.reciostore.domain.model.Address

@Composable
fun ClientAddressListContent(
    navHostController: NavHostController, padding: PaddingValues, addressList: List<Address>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        items(items = addressList) { address -> ClientAddressListItem(navHostController, address) }
    }
}