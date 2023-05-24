package com.edival.reciostore.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class ProductHasImages(
    val id: String? = null, val id_product: String? = null, var img_url: String? = null
) {
    fun toJson(): String {
        return Gson().toJson(
            ProductHasImages(
                id = id,
                id_product = id_product,
                img_url = if (!img_url.isNullOrBlank()) URLEncoder.encode(
                    img_url, StandardCharsets.UTF_8.toString()
                ) else ""
            )
        )
    }

    companion object {
        fun fromJson(data: String): ProductHasImages {
            return Gson().fromJson(data, ProductHasImages::class.java)
        }
    }
}