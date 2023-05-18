package com.edival.reciostore.presentation.screens.admin.order.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.domain.model.Order
import com.edival.reciostore.domain.useCase.orders.OrdersUseCase
import com.edival.reciostore.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminOrderDetailViewModel @Inject constructor(
    private val ordersUseCase: OrdersUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {
    var ordersStatusResponse by mutableStateOf<Resource<Order>?>(null)
        private set
    var totalToPay by mutableStateOf(0.0)
        private set
    var order by mutableStateOf<Order?>(null)
        private set

    init {
        savedStateHandle.get<String>("order")?.let { orderStr ->
            order = Order.fromJson(orderStr)
            order!!.ohp?.forEach { ohp ->
                totalToPay += (ohp.quantity * (ohp.product?.price ?: 0.0))
            }
        }
    }

    fun updateOrderStatus(idOrder: String?): Job = viewModelScope.launch {
        idOrder?.let { id ->
            ordersStatusResponse = Resource.Loading
            ordersUseCase.updateOrderStatusUseCase(id).also { result ->
                ordersStatusResponse = result
            }
        }
    }
}