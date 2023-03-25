package com.edival.reciostore.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class Rol(val id: String, val name: String, val img: String, val route: String) {
    fun toJson(): String = Gson().toJson(
        Rol(
            id,
            name,
            if (img.isNotBlank()) URLEncoder.encode(img, StandardCharsets.UTF_8.toString()) else "",
            route
        )
    )

    companion object {
        fun fromJson(data: String): Rol = Gson().fromJson(data, Rol::class.java)
    }
}