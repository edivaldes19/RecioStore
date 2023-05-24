package com.edival.reciostore.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class ShoppingBagProduct(
    val id: String = "",
    val name: String = "",
    val id_category: String = "",
    val img: String = "",
    val price: Double = 0.0,
    var quantity: Int = 1
) {
    fun toJson(): String {
        return Gson().toJson(
            ShoppingBagProduct(
                id = id,
                name = name,
                id_category = id_category,
                img = if (img.isNotBlank()) URLEncoder.encode(
                    img, StandardCharsets.UTF_8.toString()
                ) else "",
                price = price,
                quantity = quantity
            )
        )
    }

    companion object {
        fun fromJson(data: String): ShoppingBagProduct {
            return Gson().fromJson(data, ShoppingBagProduct::class.java)
        }
    }
}