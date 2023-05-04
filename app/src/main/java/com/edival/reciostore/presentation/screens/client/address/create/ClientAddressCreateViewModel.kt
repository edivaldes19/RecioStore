package com.edival.reciostore.presentation.screens.client.address.create

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.Address
import com.edival.reciostore.domain.useCase.address.AddressUseCase
import com.edival.reciostore.domain.useCase.auth.AuthUseCase
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.screens.client.address.ClientAddressState
import com.edival.reciostore.presentation.screens.client.address.mapper.toAddress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientAddressCreateViewModel @Inject constructor(
    private val addressUseCase: AddressUseCase, private val authUseCase: AuthUseCase
) : ViewModel() {
    var state by mutableStateOf(ClientAddressState())
        private set
    var addressResponse by mutableStateOf<Resource<Address>?>(null)
        private set
    var errorMessage by mutableStateOf("")
    fun getSessionData(): Job = viewModelScope.launch {
        authUseCase.getSessionDataUseCase().first().user.also { userNull ->
            userNull?.let { user ->
                state = state.copy(id_user = user.id.orEmpty())
            }
        }
    }

    fun createAddress(): Job = viewModelScope.launch {
        addressResponse = Resource.Loading
        addressUseCase.createAddressUseCase(state.toAddress()).also { result ->
            addressResponse = result
        }
    }

    fun onAddressInput(address: String) {
        state = state.copy(address = address)
    }

    fun onNeighborhoodInput(neighborhood: String) {
        state = state.copy(neighborhood = neighborhood)
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

            else -> isValid(true)
        }
    }

    fun clearForm() {
        state = state.copy(address = "", neighborhood = "")
        addressResponse = null
    }
}