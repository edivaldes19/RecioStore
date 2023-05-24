package com.edival.reciostore.domain.model

import com.google.gson.Gson

data class Product(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val price: Double = 0.0,
    val id_category: String? = null,
    var phi: List<ProductHasImages>? = null
) {
    fun toJson(): String {
        return Gson().toJson(
            Product(id = id,
                name = name,
                description = description,
                price = price,
                id_category = id_category,
                phi = phi?.map { images -> ProductHasImages.fromJson(images.toJson()) })
        )
    }

    companion object {
        fun fromJson(data: String): Product = Gson().fromJson(data, Product::class.java)
    }
}