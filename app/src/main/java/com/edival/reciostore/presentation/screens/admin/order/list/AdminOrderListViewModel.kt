package com.edival.reciostore.presentation.screens.admin.order.list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.domain.model.Order
import com.edival.reciostore.domain.useCase.orders.OrdersUseCase
import com.edival.reciostore.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminOrderListViewModel @Inject constructor(private val ordersUseCase: OrdersUseCase) :
    ViewModel() {
    var ordersResponse by mutableStateOf<Resource<List<Order>>?>(null)
        private set

    init {
        viewModelScope.launch {
            ordersResponse = Resource.Loading
            ordersUseCase.getOrdersUseCase().collect { result ->
                ordersResponse = result
                Log.d("getOrders", "REMOTE: $result")
            }
        }
    }
}