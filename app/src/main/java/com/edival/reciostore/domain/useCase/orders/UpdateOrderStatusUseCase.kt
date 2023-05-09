package com.edival.reciostore.domain.useCase.orders

import com.edival.reciostore.domain.model.Order
import com.edival.reciostore.domain.repository.OrdersRepository
import com.edival.reciostore.domain.util.Resource

class UpdateOrderStatusUseCase(private val ordersRepository: OrdersRepository) {
    suspend operator fun invoke(id: String): Resource<Order> {
        return ordersRepository.updateOrderStatus(id)
    }
}