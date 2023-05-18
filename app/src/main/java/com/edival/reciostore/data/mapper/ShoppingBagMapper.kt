package com.edival.reciostore.data.mapper

import com.edival.reciostore.data.dataSource.local.entity.ShoppingBagProductEntity
import com.edival.reciostore.domain.model.ShoppingBagProduct

fun ShoppingBagProduct.toShoppingBagEntity(): ShoppingBagProductEntity {
    return ShoppingBagProductEntity(
        id = id,
        name = name,
        id_category = id_category,
        img = img,
        price = price,
        quantity = quantity
    )
}

fun ShoppingBagProductEntity.toShoppingBagProduct(): ShoppingBagProduct {
    return ShoppingBagProduct(
        id = id,
        name = name,
        id_category = id_category,
        img = img,
        price = price,
        quantity = quantity
    )
}