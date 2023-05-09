package com.edival.reciostore.presentation.screens.client.order.list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.domain.model.Order
import com.edival.reciostore.domain.useCase.auth.AuthUseCase
import com.edival.reciostore.domain.useCase.orders.OrdersUseCase
import com.edival.reciostore.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientOrderListViewModel @Inject constructor(
    private val ordersUseCase: OrdersUseCase, private val authUseCase: AuthUseCase
) : ViewModel() {
    var ordersByClientResponse by mutableStateOf<Resource<List<Order>>?>(null)
        private set

    init {
        viewModelScope.launch {
            ordersByClientResponse = Resource.Loading
            authUseCase.getSessionDataUseCase().first().also { data ->
                if (!data.token.isNullOrBlank()) {
                    data.user.also { userNull ->
                        if (userNull != null && !userNull.id.isNullOrBlank()) {
                            ordersUseCase.getOrdersByClientUseCase(userNull.id).collect { result ->
                                ordersByClientResponse = result
                                Log.d("getOrdersByClient", "REMOTE: $result")
                            }
                        }
                    }
                }
            }
        }
    }
}