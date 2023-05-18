package com.edival.reciostore.presentation.screens.profile.change_password

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.useCase.auth.AuthUseCase
import com.edival.reciostore.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileChangePasswordViewModel @Inject constructor(
    private val authUseCase: AuthUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(ChangePasswordState())
        private set
    var changePasswordResponse by mutableStateOf<Resource<User>?>(null)
        private set
    var enabledBtn by mutableStateOf(true)
        private set
    var errorMessage by mutableStateOf("")

    init {
        savedStateHandle.get<String>("id_user")?.let { idUserStr ->
            state = state.copy(idUser = idUserStr)
        }
    }

    fun updatePassword(): Job = viewModelScope.launch {
        if (state.idUser.isNotBlank()) {
            enabledBtn = false
            changePasswordResponse = Resource.Loading
            authUseCase.updatePasswordUseCase(state.idUser, state.oldPassword, state.newPassword)
                .also { result -> changePasswordResponse = result }
        }
    }

    fun onOldPasswordInput(oldPassword: String) {
        state = state.copy(oldPassword = oldPassword)
    }

    fun onNewPasswordInput(newPassword: String) {
        state = state.copy(newPassword = newPassword)
    }

    fun validateForm(ctx: Context, isValid: (Boolean) -> Unit) {
        when {
            state.oldPassword.length < 6 || state.oldPassword.isBlank() -> {
                errorMessage = ctx.getString(R.string.invalid_password)
                isValid(false)
            }

            state.newPassword.length < 6 || state.newPassword.isBlank() -> {
                errorMessage = ctx.getString(R.string.invalid_password)
                isValid(false)
            }

            else -> isValid(true)
        }
    }

    fun clearForm(isOnlyForm: Boolean) {
        if (isOnlyForm) {
            state = state.copy(oldPassword = "", newPassword = "")
            changePasswordResponse = null
        }
        enabledBtn = true
    }
}