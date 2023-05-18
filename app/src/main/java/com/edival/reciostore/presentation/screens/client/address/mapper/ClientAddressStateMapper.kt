package com.edival.reciostore.presentation.screens.client.address.mapper

import com.edival.reciostore.domain.model.Address
import com.edival.reciostore.presentation.screens.client.address.ClientAddressState

fun ClientAddressState.toAddress(): Address {
    return Address(
        address = address.trim(), neighborhood = neighborhood.trim(), id_user = id_user.trim()
    )
}