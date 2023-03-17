package com.edival.reciostore.presentation.screens.auth.register

import android.content.Context
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.edival.reciostore.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set
    var errorMessage by mutableStateOf("")
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

    fun validateForm(ctx: Context) {
        when {
            state.name.length < 5 || state.name.isBlank() -> errorMessage =
                ctx.getString(R.string.invalid_name)
            state.surname.length < 5 || state.surname.isBlank() -> errorMessage =
                ctx.getString(R.string.invalid_surname)
            state.phone.length < 10 || state.phone.isBlank() -> errorMessage =
                ctx.getString(R.string.invalid_phone)
            !Patterns.EMAIL_ADDRESS.matcher(state.email).matches() -> errorMessage =
                ctx.getString(R.string.invalid_email)
            state.password.length < 6 || state.password.isBlank() -> errorMessage =
                ctx.getString(R.string.invalid_password)
            state.password != state.confirmPassword -> errorMessage =
                ctx.getString(R.string.invalid_password_matches)
        }
    }
}