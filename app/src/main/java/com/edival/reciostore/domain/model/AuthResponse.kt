package com.edival.reciostore.domain.model

import com.google.gson.Gson

data class AuthResponse(var user: User? = null, val token: String? = null) {
    fun toJson(): String = Gson().toJson(this)

    companion object {
        fun fromJson(data: String): AuthResponse = Gson().fromJson(data, AuthResponse::class.java)
    }
}