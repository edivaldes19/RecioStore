package com.edival.reciostore.domain.model

import com.google.gson.Gson

data class OrderHasProducts(
    val id_order: String? = null,
    val id_product: String? = null,
    val quantity: Int = 1,
    val product: Product? = null
) {
    fun toJson(): String {
        return Gson().toJson(
            OrderHasProducts(id_order = id_order,
                id_product = id_product,
                quantity = quantity,
                product = product?.let { prod -> Product.fromJson(prod.toJson()) })
        )
    }

    companion object {
        fun fromJson(data: String): OrderHasProducts =
            Gson().fromJson(data, OrderHasProducts::class.java)
    }
}