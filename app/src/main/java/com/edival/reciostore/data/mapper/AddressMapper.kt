package com.edival.reciostore.data.mapper

import com.edival.reciostore.data.dataSource.local.entity.AddressEntity
import com.edival.reciostore.domain.model.Address

fun AddressEntity.toAddress(): Address {
    return Address(id = id, address = address, neighborhood = neighborhood, id_user = id_user)
}

fun Address.toAddressEntity(): AddressEntity {
    return AddressEntity(
        id = id.orEmpty(),
        address = address.orEmpty(),
        neighborhood = neighborhood.orEmpty(),
        id_user = id_user.orEmpty()
    )
}