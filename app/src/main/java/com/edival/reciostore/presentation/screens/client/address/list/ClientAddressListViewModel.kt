package com.edival.reciostore.presentation.screens.client.address.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.domain.model.Address
import com.edival.reciostore.domain.model.Order
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.useCase.address.AddressUseCase
import com.edival.reciostore.domain.useCase.auth.AuthUseCase
import com.edival.reciostore.domain.useCase.orders.OrdersUseCase
import com.edival.reciostore.domain.useCase.shopping_bag.ShoppingBagUseCase
import com.edival.reciostore.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientAddressListViewModel @Inject constructor(
    private val addressUseCase: AddressUseCase,
    private val authUseCase: AuthUseCase,
    private val ordersUseCase: OrdersUseCase,
    private val shoppingBagUseCase: ShoppingBagUseCase
) : ViewModel() {
    var addressResponse by mutableStateOf<Resource<List<Address>>?>(null)
        private set
    var deleteAddressResponse by mutableStateOf<Resource<Unit>?>(null)
        private set
    var orderResponse by mutableStateOf<Resource<Order>?>(null)
        private set
    var selectedAddress by mutableStateOf("")
        private set
    var user by mutableStateOf<User?>(null)
        private set

    init {
        viewModelScope.launch {
            authUseCase.getSessionDataUseCase().first().also { data ->
                if (!data.token.isNullOrBlank()) {
                    user = data.user
                    if (user != null && !user!!.id.isNullOrBlank()) {
                        addressResponse = Resource.Loading
                        addressUseCase.getAddressByUserUseCase(user!!.id!!).collect { result ->
                            addressResponse = result
                            selectedAddress = user!!.address?.id.orEmpty()
                        }
                    }
                }
            }
        }
    }

    fun createOrder(): Job = viewModelScope.launch {
        if (user != null && !user!!.id.isNullOrBlank()) {
            shoppingBagUseCase.getProductsBagUseCase().first().also { sbProducts ->
                val order = Order(
                    id_client = user!!.id,
                    id_address = user!!.address?.id.orEmpty(),
                    products = sbProducts
                )
                ordersUseCase.createOrderUseCase(order).also { result -> orderResponse = result }
            }
        }
    }

    fun onSelectedAddressInput(address: Address): Job = viewModelScope.launch {
        selectedAddress = address.id.orEmpty()
        user?.let { u ->
            u.address = address
            authUseCase.updateSessionUseCase(u)
        }
    }

    fun deleteAddress(idAddress: String?): Job = viewModelScope.launch {
        idAddress?.let { id ->
            deleteAddressResponse = Resource.Loading
            addressUseCase.deleteAddressUseCase(id).also { result ->
                deleteAddressResponse = result
            }
        }
    }

    fun emptyShoppingBag(): Job = viewModelScope.launch {
        shoppingBagUseCase.emptyShoppingBagUseCase()
    }
}