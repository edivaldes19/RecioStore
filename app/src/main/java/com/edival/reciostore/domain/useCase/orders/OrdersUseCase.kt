package com.edival.reciostore.domain.useCase.orders

data class OrdersUseCase(
    val getOrdersUseCase: GetOrdersUseCase,
    val getOrdersByClientUseCase: GetOrdersByClientUseCase,
    val createOrderUseCase: CreateOrderUseCase,
    val updateOrderStatusUseCase: UpdateOrderStatusUseCase
)