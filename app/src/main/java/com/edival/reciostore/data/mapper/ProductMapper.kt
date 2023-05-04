package com.edival.reciostore.data.mapper

import com.edival.reciostore.data.dataSource.local.entity.ProductEntity
import com.edival.reciostore.domain.model.Product

fun ProductEntity.toProduct(): Product {
    return Product(
        id = id,
        name = name,
        description = description,
        img1 = img1,
        img2 = img2,
        price = price,
        id_category = id_category,
        images_to_update = null
    )
}

fun Product.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id.orEmpty(),
        name = name.orEmpty(),
        description = description.orEmpty(),
        img1 = img1.orEmpty(),
        img2 = img2.orEmpty(),
        price = price,
        id_category = id_category.orEmpty()
    )
}