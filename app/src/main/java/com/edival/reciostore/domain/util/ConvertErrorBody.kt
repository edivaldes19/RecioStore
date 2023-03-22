package com.edival.reciostore.domain.util

import com.edival.reciostore.domain.model.ErrorResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody

object ConvertErrorBody {
    fun convert(errorBody: ResponseBody?): ErrorResponse? {
        return try {
            errorBody?.source()?.let { source ->
                val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
                val moshiAdapter = moshi.adapter(ErrorResponse::class.java)
                moshiAdapter.fromJson(source)
            }
        } catch (e: Exception) {
            null
        }
    }
}