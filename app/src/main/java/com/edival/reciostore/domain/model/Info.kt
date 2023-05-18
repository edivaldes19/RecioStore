package com.edival.reciostore.domain.model

import com.google.gson.Gson

data class Info(val key: String? = null, val value: String? = null) {
    fun toJson(): String = Gson().toJson(this)

    companion object {
        fun fromJson(data: String): Address = Gson().fromJson(data, Address::class.java)
    }
}