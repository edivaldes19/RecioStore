package com.edival.reciostore.data.dataSource.remote

import com.edival.reciostore.data.dataSource.remote.service.OrdersService
import com.edival.reciostore.domain.model.Order
import retrofit2.Response

class OrdersRemoteDataSourceImpl(private val ordersService: OrdersService) :
    OrdersRemoteDataSource {
    override suspend fun getOrders(): Response<List<Order>> = ordersService.getOrders()
    override suspend fun getOrdersByClient(idClient: String): Response<List<Order>> {
        return ordersService.getOrdersByClient(idClient)
    }

    override suspend fun createOrder(order: Order): Response<Order> {
        return ordersService.createOrder(order)
    }

    override suspend fun updateOrderStatus(id: String): Response<Order> {
        return ordersService.updateOrderStatus(id)
    }
}