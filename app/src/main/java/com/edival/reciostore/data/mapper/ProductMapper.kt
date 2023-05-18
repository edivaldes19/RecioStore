package com.edival.reciostore.data.mapper

import com.edival.reciostore.data.dataSource.local.entity.ProductEntity
import com.edival.reciostore.domain.model.Product

fun ProductEntity.toProduct(): Product {
    return Product(
        id = id, name = name, description = description, price = price, id_category = id_category
    )
}

fun Product.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id.orEmpty(),
        name = name.orEmpty(),
        description = description.orEmpty(),
        price = price,
        id_category = id_category.orEmpty()
    )
}