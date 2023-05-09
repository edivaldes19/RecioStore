package com.edival.reciostore.presentation.screens.roles

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.useCase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RolesViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {
    var user by mutableStateOf<User?>(null)
        private set

    init {
        viewModelScope.launch {
            authUseCase.getSessionDataUseCase().collect { data ->
                if (!data.token.isNullOrBlank()) user = data.user
            }
        }
    }

    fun saveRoleName(name: String): Job = viewModelScope.launch {
        authUseCase.saveRoleNameUseCase(name)
    }
}