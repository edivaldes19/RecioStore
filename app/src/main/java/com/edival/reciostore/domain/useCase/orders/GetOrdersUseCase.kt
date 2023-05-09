package com.edival.reciostore.domain.useCase.orders

import com.edival.reciostore.domain.model.Order
import com.edival.reciostore.domain.repository.OrdersRepository
import com.edival.reciostore.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetOrdersUseCase(private val ordersRepository: OrdersRepository) {
    operator fun invoke(): Flow<Resource<List<Order>>> = ordersRepository.getOrders()
}