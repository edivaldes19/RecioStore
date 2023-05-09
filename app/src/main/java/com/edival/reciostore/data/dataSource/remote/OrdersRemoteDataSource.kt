package com.edival.reciostore.data.dataSource.remote

import com.edival.reciostore.domain.model.Order
import retrofit2.Response

interface OrdersRemoteDataSource {
    suspend fun getOrders(): Response<List<Order>>
    suspend fun getOrdersByClient(idClient: String): Response<List<Order>>
    suspend fun createOrder(order: Order): Response<Order>
    suspend fun updateOrderStatus(id: String): Response<Order>
}