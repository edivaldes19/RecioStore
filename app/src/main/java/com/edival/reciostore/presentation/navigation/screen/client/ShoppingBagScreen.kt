package com.edival.reciostore.presentation.navigation.screen.client

sealed class ShoppingBagScreen(val route: String) {
    object ShoppingBag : ShoppingBagScreen("client/shoppingBag")
    object AddressList : ShoppingBagScreen("client/address/list")
    object AddressCreate : ShoppingBagScreen("client/address/create")
    object AddressUpdate : ShoppingBagScreen("client/address/update/{address}") {
        fun passAddress(address: String): String = "client/address/update/$address"
    }
}