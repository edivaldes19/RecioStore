package com.edival.reciostore.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class DeleteResponse(val url: String? = null) {
    fun toJson(): String = Gson().toJson(
        DeleteResponse(
            url = if (!url.isNullOrBlank()) URLEncoder.encode(
                url, StandardCharsets.UTF_8.toString()
            ) else ""
        )
    )

    companion object {
        fun fromJson(data: String): DeleteResponse {
            return Gson().fromJson(data, DeleteResponse::class.java)
        }
    }
}