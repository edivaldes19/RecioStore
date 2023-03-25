package com.edival.reciostore.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class User(
    val id: String? = null,
    var name: String,
    var surname: String,
    var phone: String,
    val email: String? = null,
    val password: String? = null,
    var img: String? = null,
    val notification_token: String? = null,
    val roles: List<Rol> = listOf()
) {
    fun toJson(): String = Gson().toJson(
        User(id, name, surname, phone, email, password, if (!img.isNullOrBlank()) URLEncoder.encode(
            img, StandardCharsets.UTF_8.toString()
        ) else "", notification_token, roles.map { rol -> Rol.fromJson(rol.toJson()) })
    )

    companion object {
        fun fromJson(data: String): User = Gson().fromJson(data, User::class.java)
    }
}