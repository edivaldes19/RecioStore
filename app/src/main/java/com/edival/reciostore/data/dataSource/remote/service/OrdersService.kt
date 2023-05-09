package com.edival.reciostore.data.dataSource.remote.service

import com.edival.reciostore.core.Config
import com.edival.reciostore.domain.model.Order
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface OrdersService {
    @GET(Config.ORDERS_URL)
    suspend fun getOrders(): Response<List<Order>>

    @GET("${Config.ORDERS_URL}/getOrdersByClient/{id_client}")
    suspend fun getOrdersByClient(@Path("id_client") idClient: String): Response<List<Order>>

    @POST("${Config.ORDERS_URL}/createOrder")
    suspend fun createOrder(@Body order: Order): Response<Order>

    @PUT("${Config.ORDERS_URL}/updateOrderStatus/{id}")
    suspend fun updateOrderStatus(@Path("id") id: String): Response<Order>
}