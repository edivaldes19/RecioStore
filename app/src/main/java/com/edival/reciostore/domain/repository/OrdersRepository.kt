package com.edival.reciostore.domain.repository

import com.edival.reciostore.domain.model.Order
import com.edival.reciostore.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface OrdersRepository {
    fun getOrders(): Flow<Resource<List<Order>>>
    fun getOrdersByClient(idClient: String): Flow<Resource<List<Order>>>
    suspend fun createOrder(order: Order): Resource<Order>
    suspend fun updateOrderStatus(id: String): Resource<Order>
}