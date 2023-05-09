package com.edival.reciostore.data.repository

import com.edival.reciostore.data.dataSource.remote.OrdersRemoteDataSource
import com.edival.reciostore.domain.model.Order
import com.edival.reciostore.domain.repository.OrdersRepository
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.domain.util.ResponseToRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OrdersRepositoryImpl(private val remoteDS: OrdersRemoteDataSource) : OrdersRepository {
    override fun getOrders(): Flow<Resource<List<Order>>> = flow {
        emit(ResponseToRequest.send(remoteDS.getOrders()))
    }

    override fun getOrdersByClient(idClient: String): Flow<Resource<List<Order>>> = flow {
        emit(ResponseToRequest.send(remoteDS.getOrdersByClient(idClient)))
    }

    override suspend fun createOrder(order: Order): Resource<Order> {
        return ResponseToRequest.send(remoteDS.createOrder(order))
    }

    override suspend fun updateOrderStatus(id: String): Resource<Order> {
        return ResponseToRequest.send(remoteDS.updateOrderStatus(id))
    }
}