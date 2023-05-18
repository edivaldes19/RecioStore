package com.edival.reciostore.domain.model

import com.google.gson.Gson

data class RoleAssignment(val idUser: String, val toAdmin: Boolean) {
    fun toJson(): String = Gson().toJson(this)

    companion object {
        fun fromJson(data: String): RoleAssignment {
            return Gson().fromJson(data, RoleAssignment::class.java)
        }
    }
}