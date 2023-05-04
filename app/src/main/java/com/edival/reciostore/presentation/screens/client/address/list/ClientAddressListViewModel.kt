package com.edival.reciostore.presentation.screens.client.address.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.domain.model.Address
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.useCase.address.AddressUseCase
import com.edival.reciostore.domain.useCase.auth.AuthUseCase
import com.edival.reciostore.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientAddressListViewModel @Inject constructor(
    private val addressUseCase: AddressUseCase, private val authUseCase: AuthUseCase
) : ViewModel() {
    var addressResponse by mutableStateOf<Resource<List<Address>>?>(null)
        private set
    var deleteAddressResponse by mutableStateOf<Resource<Unit>?>(null)
        private set
    var selectedAddress by mutableStateOf("")
        private set
    private var user: User? = null
    fun getSessionData(): Job = viewModelScope.launch {
        user = authUseCase.getSessionDataUseCase().first().user
        user?.let { u ->
            if (!u.id.isNullOrBlank() && u.address != null) {
                addressResponse = Resource.Loading
                addressUseCase.getAddressByUserUseCase(u.id).collect { result ->
                    addressResponse = result
                    selectedAddress = u.address!!.id.orEmpty()
                }
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
}