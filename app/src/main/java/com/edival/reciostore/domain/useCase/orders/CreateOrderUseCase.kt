package com.edival.reciostore.domain.useCase.orders

import com.edival.reciostore.domain.model.Order
import com.edival.reciostore.domain.repository.OrdersRepository
import com.edival.reciostore.domain.util.Resource

class CreateOrderUseCase(private val ordersRepository: OrdersRepository) {
    suspend operator fun invoke(order: Order): Resource<Order> = ordersRepository.createOrder(order)
}