package com.edival.reciostore.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class Category(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val img: String? = null
) {
    fun toJson(): String {
        return Gson().toJson(
            Category(
                id = id,
                name = name,
                description = description,
                img = if (!img.isNullOrBlank()) URLEncoder.encode(
                    img, StandardCharsets.UTF_8.toString()
                ) else ""
            )
        )
    }

    companion object {
        fun fromJson(data: String): Category = Gson().fromJson(data, Category::class.java)
    }
}