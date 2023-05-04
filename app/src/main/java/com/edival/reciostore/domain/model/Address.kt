package com.edival.reciostore.domain.model

import com.google.gson.Gson

data class Address(
    val id: String? = null,
    val address: String? = null,
    val neighborhood: String? = null,
    val id_user: String? = null
) {
    fun toJson(): String = Gson().toJson(this)

    companion object {
        fun fromJson(data: String): Address = Gson().fromJson(data, Address::class.java)
    }
}