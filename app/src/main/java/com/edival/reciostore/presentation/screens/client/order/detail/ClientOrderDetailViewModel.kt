package com.edival.reciostore.presentation.screens.client.order.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.edival.reciostore.domain.model.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClientOrderDetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    ViewModel() {
    var totalToPay by mutableStateOf(0.0)
        private set
    var order by mutableStateOf<Order?>(null)
        private set

    init {
        savedStateHandle.get<String>("order")?.let { orderStr ->
            Log.d("ClientOrderDetailViewModel", "ORDER: $orderStr")
//            order = Order.fromJson(orderStr)
//            order!!.ohp?.forEach { ohp ->
//                totalToPay += (ohp.quantity * (ohp.product?.price ?: 0.0))
//            }
        }
    }
}