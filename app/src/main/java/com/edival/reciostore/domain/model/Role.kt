package com.edival.reciostore.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class Role(
    val id: String? = null,
    val name: String? = null,
    val img: String? = null,
    val route: String? = null
) {
    fun toJson(): String = Gson().toJson(
        Role(
            id = id, name = name, img = if (!img.isNullOrBlank()) URLEncoder.encode(
                img, StandardCharsets.UTF_8.toString()
            ) else "", route = route
        )
    )

    companion object {
        fun fromJson(data: String): Role = Gson().fromJson(data, Role::class.java)
    }
}