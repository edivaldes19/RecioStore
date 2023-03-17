package com.edival.reciostore.presentation.screens.auth.login

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
class LoginViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(LoginState())
        private set
    var errorMessage by mutableStateOf("")
    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }

    fun validateForm(ctx: Context) {
        when {
            !Patterns.EMAIL_ADDRESS.matcher(state.email).matches() -> errorMessage =
                ctx.getString(R.string.invalid_email)
            state.password.length < 6 || state.password.isBlank() -> errorMessage =
                ctx.getString(R.string.invalid_password)
        }
    }
}