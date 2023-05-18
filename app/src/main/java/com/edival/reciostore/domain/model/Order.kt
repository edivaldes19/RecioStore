package com.edival.reciostore.domain.model

import com.google.gson.Gson

data class Order(
    val id: String? = null,
    val id_client: String? = null,
    val id_address: String? = null,
    val status: String? = null,
    val user: User? = null,
    val address: Address? = null,
    val ohp: List<OrderHasProducts>? = null,
    val created_at: String? = null,
    val products: List<ShoppingBagProduct>? = null
) {
    fun toJson(): String {
        return Gson().toJson(
            Order(
                id = id,
                id_client = id_client,
                id_address = id_address,
                status = status,
                user = user?.let { client -> User.fromJson(client.toJson()) },
                address = address,
                ohp = ohp?.map { orderHasProducts -> OrderHasProducts.fromJson(orderHasProducts.toJson()) },
                created_at = created_at
            )
        )
    }

    companion object {
        fun fromJson(data: String): Order = Gson().fromJson(data, Order::class.java)
    }
}