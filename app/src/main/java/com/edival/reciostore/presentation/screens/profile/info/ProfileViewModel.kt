package com.edival.reciostore.presentation.screens.profile.info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.useCase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {
    var user by mutableStateOf<User?>(null)
        private set
    var roleName by mutableStateOf<String?>(null)
        private set

    init {
        viewModelScope.launch {
            authUseCase.getSessionDataUseCase().collect { data ->
                if (!data.token.isNullOrBlank()) {
                    user = data.user
                    authUseCase.getRoleNameUseCase().first().also { name -> roleName = name }
                }
            }
        }
    }

    fun logOut(): Job = viewModelScope.launch { authUseCase.logOutUseCase() }
}