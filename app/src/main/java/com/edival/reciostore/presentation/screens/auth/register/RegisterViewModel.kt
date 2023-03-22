package com.edival.reciostore.presentation.screens.auth.register

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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set
    var errorMessage by mutableStateOf("")
    var signUpResource by mutableStateOf<Resource<AuthResponse>?>(null)
        private set

    fun saveSession(authResponse: AuthResponse): Job = viewModelScope.launch {
        authUseCase.saveSessionUseCase(authResponse)
    }

    fun signUp(): Job = viewModelScope.launch {
        val user = User(
            name = state.name,
            surname = state.surname,
            phone = state.phone,
            email = state.email,
            password = state.password
        )
        signUpResource = Resource.Loading
        val result = authUseCase.signUpUseCase(user)
        signUpResource = result
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
        }
        isValid(true)
    }
}