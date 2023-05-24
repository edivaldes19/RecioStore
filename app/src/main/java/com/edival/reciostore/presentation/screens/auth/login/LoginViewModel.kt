package com.edival.reciostore.presentation.screens.auth.login

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
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
    var updateNotTokResponse by mutableStateOf<Resource<User>?>(null)
        private set
    var tokenResponse by mutableStateOf<Resource<String>?>(null)
        private set
    var errorMessage by mutableStateOf("")
        private set
    var enabledBtn by mutableStateOf(true)
        private set

    fun saveSession(authResponse: AuthResponse): Job = viewModelScope.launch {
        authUseCase.saveSessionUseCase(authResponse)
    }

    fun logIn(): Job = viewModelScope.launch {
        enabledBtn = false
        logInResponse = Resource.Loading
        authUseCase.loginUseCase(state.email.trim(), state.password.trim()).also { result ->
            logInResponse = result
            Log.d("logIn", "$result")
        }
    }

    fun updateNotificationToken(idUser: String?, token: String): Job = viewModelScope.launch {
        if (!idUser.isNullOrBlank()) {
            updateNotTokResponse = Resource.Loading
            authUseCase.updateNotificationTokenUseCase(idUser, token).also { result ->
                updateNotTokResponse = result
                Log.d("updateNotificationToken", "$result")
            }
        } else updateNotTokResponse = Resource.Failure("idUser cannot be null")
    }

    fun createToken(): Job = viewModelScope.launch {
        tokenResponse = Resource.Loading
        authUseCase.createTokenUseCase().also { result ->
            tokenResponse = result
            Log.d("createToken", "$result")
        }
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }

    fun showMsg(show: () -> Unit) {
        if (errorMessage.isNotBlank()) {
            show()
            errorMessage = ""
        }
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

    fun clearForm() {
        logInResponse = null
        updateNotTokResponse = null
        tokenResponse = null
        enabledBtn = true
    }
}