package com.edival.reciostore.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class Product(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val price: Double = 0.0,
    val id_category: String? = null,
    val img1: String? = null,
    val img2: String? = null,
    val images_to_update: List<Int>? = null
) {
    fun toJson(): String = Gson().toJson(
        Product(
            id = id,
            name = name,
            description = description,
            price = price,
            id_category = id_category,
            img1 = if (!img1.isNullOrBlank()) URLEncoder.encode(
                img1, StandardCharsets.UTF_8.toString()
            ) else "",
            img2 = if (!img2.isNullOrBlank()) URLEncoder.encode(
                img2, StandardCharsets.UTF_8.toString()
            ) else "",
            images_to_update = images_to_update
        )
    )

    companion object {
        fun fromJson(data: String): Product = Gson().fromJson(data, Product::class.java)
    }
}