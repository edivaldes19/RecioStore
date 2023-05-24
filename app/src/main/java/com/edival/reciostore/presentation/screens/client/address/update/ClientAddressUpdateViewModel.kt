package com.edival.reciostore.presentation.screens.client.address.update

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.Address
import com.edival.reciostore.domain.useCase.address.AddressUseCase
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.screens.client.address.ClientAddressState
import com.edival.reciostore.presentation.screens.client.address.mapper.toAddress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientAddressUpdateViewModel @Inject constructor(
    private val addressUseCase: AddressUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(ClientAddressState())
        private set
    var addressResponse by mutableStateOf<Resource<Address>?>(null)
        private set
    var errorMessage by mutableStateOf("")
        private set
    var enabledBtn by mutableStateOf(true)
        private set
    private var idAddress: String? = null

    init {
        savedStateHandle.get<String>("address")?.let { addressStr ->
            Address.fromJson(addressStr).also { address ->
                idAddress = address.id
                state = state.copy(
                    address = address.address.orEmpty(),
                    neighborhood = address.neighborhood.orEmpty(),
                    id_user = address.id_user.orEmpty()
                )
            }
        }
    }

    fun updateAddress(): Job = viewModelScope.launch {
        enabledBtn = false
        addressResponse = Resource.Loading
        addressUseCase.updateAddressUseCase(idAddress!!, state.toAddress()).also { result ->
            addressResponse = result
        }
    }

    fun onAddressInput(address: String) {
        state = state.copy(address = address)
    }

    fun onNeighborhoodInput(neighborhood: String) {
        state = state.copy(neighborhood = neighborhood)
    }

    fun showMsg(show: () -> Unit) {
        if (errorMessage.isNotBlank()) {
            show()
            errorMessage = ""
        }
    }

    fun validateForm(ctx: Context, isValid: (Boolean) -> Unit) {
        when {
            state.address.length < 5 || state.address.isBlank() -> {
                errorMessage = ctx.getString(R.string.invalid_address)
                isValid(false)
            }

            state.neighborhood.length < 5 || state.neighborhood.isBlank() -> {
                errorMessage = ctx.getString(R.string.invalid_neighborhood)
                isValid(false)
            }

            idAddress.isNullOrBlank() -> {
                errorMessage = ctx.getString(R.string.id_cannot_be_null)
                isValid(false)
            }

            else -> isValid(true)
        }
    }

    fun resetForm() {
        addressResponse = null
        enabledBtn = true
    }
}