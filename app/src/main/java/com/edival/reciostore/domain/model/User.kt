package com.edival.reciostore.domain.model

import com.google.gson.Gson

data class User(
    val id: String? = null,
    val name: String,
    val surname: String,
    val phone: String,
    val email: String,
    val password: String,
    val img: String? = null,
    val notification_token: String? = null,
    val roles: MutableList<Rol>? = null
) {
    fun toJson(): String = Gson().toJson(this)

    companion object {
        fun fromJson(data: String): User = Gson().fromJson(data, User::class.java)
    }
}