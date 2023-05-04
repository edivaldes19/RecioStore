package com.edival.reciostore.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class User(
    val id: String? = null,
    var name: String? = null,
    var surname: String? = null,
    var phone: String? = null,
    val email: String? = null,
    val password: String? = null,
    var img: String? = null,
    val notification_token: String? = null,
    val roles: List<Rol>? = null,
    var address: Address? = null
) {
    fun toJson(): String = Gson().toJson(
        User(
            id = id,
            name = name,
            surname = surname,
            phone = phone,
            email = email,
            password = password,
            img = if (!img.isNullOrBlank()) URLEncoder.encode(
                img, StandardCharsets.UTF_8.toString()
            ) else "",
            notification_token = notification_token,
            roles = roles?.map { rol -> Rol.fromJson(rol.toJson()) },
            address = address
        )
    )

    companion object {
        fun fromJson(data: String): User = Gson().fromJson(data, User::class.java)
    }
}