package com.edival.reciostore.presentation.screens.auth.signup

import android.content.Context
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
import com.edival.reciostore.presentation.screens.auth.signup.mapper.toUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {
    var state by mutableStateOf(SignUpState())
        private set
    var signUpResource by mutableStateOf<Resource<AuthResponse>?>(null)
        private set
    var updateNotTokResponse by mutableStateOf<Resource<User>?>(null)
        private set
    var tokenResponse by mutableStateOf<Resource<String>?>(null)
        private set
    var errorMessage by mutableStateOf("")
        private set
    var enabledBtn by mutableStateOf(true)
        private set

    fun signUp(): Job = viewModelScope.launch {
        enabledBtn = false
        signUpResource = Resource.Loading
        authUseCase.signUpUseCase(state.toUser()).also { result ->
            signUpResource = result
        }
    }

    fun updateNotificationToken(idUser: String?, token: String): Job = viewModelScope.launch {
        if (!idUser.isNullOrBlank()) {
            updateNotTokResponse = Resource.Loading
            authUseCase.updateNotificationTokenUseCase(idUser, token).also { result ->
                updateNotTokResponse = result
            }
        } else updateNotTokResponse = Resource.Failure("idUser cannot be null")
    }

    fun createToken(): Job = viewModelScope.launch {
        tokenResponse = Resource.Loading
        authUseCase.createTokenUseCase().also { result ->
            tokenResponse = result
        }
    }

    fun saveSession(authResponse: AuthResponse): Job = viewModelScope.launch {
        authUseCase.saveSessionUseCase(authResponse)
    }

    fun onNameInput(name: String) {
        state = state.copy(name = name)
    }

    fun onSurnameInput(surname: String) {
        state = state.copy(surname = surname)
    }

    fun onPhoneInput(phone: String) {
        state = state.copy(phone = phone)
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }

    fun onConfirmPasswordInput(confirmPassword: String) {
        state = state.copy(confirmPassword = confirmPassword)
    }

    fun showMsg(show: () -> Unit) {
        if (errorMessage.isNotBlank()) {
            show()
            errorMessage = ""
        }
    }

    fun validateForm(ctx: Context, isValid: (Boolean) -> Unit) {
        when {
            state.name.length < 5 || state.name.isBlank() -> {
                errorMessage = ctx.getString(R.string.invalid_name)
                isValid(false)
            }

            state.surname.length < 5 || state.surname.isBlank() -> {
                errorMessage = ctx.getString(R.string.invalid_surname)
                isValid(false)
            }

            state.phone.length < 10 || state.phone.isBlank() -> {
                errorMessage = ctx.getString(R.string.invalid_phone)
                isValid(false)
            }

            !Patterns.EMAIL_ADDRESS.matcher(state.email).matches() -> {
                errorMessage = ctx.getString(R.string.invalid_email)
                isValid(false)
            }

            state.password.length < 6 || state.password.isBlank() -> {
                errorMessage = ctx.getString(R.string.invalid_password)
                isValid(false)
            }

            state.password != state.confirmPassword -> {
                errorMessage = ctx.getString(R.string.invalid_password_matches)
                isValid(false)
            }

            else -> isValid(true)
        }
    }

    fun clearForm() {
        signUpResource = null
        updateNotTokResponse = null
        tokenResponse = null
        enabledBtn = true
    }
}