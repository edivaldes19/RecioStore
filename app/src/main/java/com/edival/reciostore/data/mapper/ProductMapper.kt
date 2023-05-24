package com.edival.reciostore.data.mapper

import com.edival.reciostore.data.dataSource.local.entity.ProductEntity
import com.edival.reciostore.domain.model.Product
import com.edival.reciostore.domain.model.ProductHasImages
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

fun ProductEntity.toProduct(): Product {
    return Product(id = id,
        name = name,
        description = description,
        price = price,
        id_category = id_category,
        phi = if (phi.isNotBlank()) {
            phi.split(" - ").map { imagesStr ->
                val phiObj = ProductHasImages.fromJson(imagesStr)
                if (!phiObj.img_url.isNullOrBlank()) phiObj.img_url =
                    URLDecoder.decode(phiObj.img_url, StandardCharsets.UTF_8.toString())
                phiObj
            }
        } else null)
}

fun Product.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id.orEmpty(),
        name = name.orEmpty(),
        description = description.orEmpty(),
        price = price,
        id_category = id_category.orEmpty(),
        phi = phi?.joinToString(separator = " - ") { images -> images.toJson() }.orEmpty()
    )
}