package com.edival.reciostore.presentation.screens.auth.login

import android.content.Context
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.useCase.auth.AuthUseCase
import com.edival.reciostore.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {
    var state by mutableStateOf(LoginState())
        private set
    var logInResponse by mutableStateOf<Resource<AuthResponse>?>(null)
        private set
    var errorMessage by mutableStateOf("")
    fun saveSession(authResponse: AuthResponse): Job = viewModelScope.launch {
        authUseCase.saveSessionUseCase(authResponse)
    }

    fun logIn(): Job = viewModelScope.launch {
        logInResponse = Resource.Loading
        authUseCase.loginUseCase(state.email, state.password).also { result ->
            logInResponse = result
        }
    }

    fun saveRoleName(name: String): Job = viewModelScope.launch {
        authUseCase.saveRoleNameUseCase(name)
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }

    fun validateForm(ctx: Context, isValid: (Boolean) -> Unit) {
        when {
            !Patterns.EMAIL_ADDRESS.matcher(state.email).matches() -> {
                errorMessage = ctx.getString(R.string.invalid_email)
                isValid(false)
            }

            state.password.length < 6 || state.password.isBlank() -> {
                errorMessage = ctx.getString(R.string.invalid_password)
                isValid(false)
            }

            else -> isValid(true)
        }
    }
}