package com.edival.reciostore.presentation.screens.roles

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.useCase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RolesViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {
    var authResponse by mutableStateOf(AuthResponse())
        private set

    init {
        viewModelScope.launch {
            authUseCase.getSessionDataUseCase().collect { data ->
                if (!data.token.isNullOrBlank()) authResponse = data
            }
        }
    }
}