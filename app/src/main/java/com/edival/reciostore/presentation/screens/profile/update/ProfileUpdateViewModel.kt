package com.edival.reciostore.presentation.screens.profile.update

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.useCase.auth.AuthUseCase
import com.edival.reciostore.domain.useCase.users.UsersUseCase
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.screens.profile.update.mapper.toUser
import com.edival.reciostore.presentation.util.ComposeFileProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileUpdateViewModel @Inject constructor(
    private val authUseCase: AuthUseCase, private val usersUseCase: UsersUseCase
) : ViewModel() {
    var state by mutableStateOf(ProfileUpdateState())
        private set
    var updateResponse by mutableStateOf<Resource<User>?>(null)
        private set
    var errorMessage by mutableStateOf("")
        private set
    var enabledBtn by mutableStateOf(true)
        private set
    private var idUser: String? = null

    init {
        viewModelScope.launch {
            authUseCase.getSessionDataUseCase().collect { data ->
                if (!data.token.isNullOrBlank() && data.user != null) {
                    val user = data.user!!
                    idUser = user.id
                    state = state.copy(
                        name = user.name.orEmpty(),
                        surname = user.surname.orEmpty(),
                        phone = user.phone.orEmpty(),
                        img = user.img
                    )
                }
            }
        }
    }

    fun updateUserSession(userResponse: User): Job = viewModelScope.launch {
        authUseCase.updateSessionUseCase(userResponse)
    }

    fun updateUser(ctx: Context): Job = viewModelScope.launch {
        enabledBtn = false
        updateResponse = Resource.Loading
        if (state.imgSelected != null) {
            ComposeFileProvider.createFilesFromUris(
                ctx, listOf(state.imgSelected!!)
            ) { zipFiles, errMsg ->
                when {
                    !errMsg.isNullOrBlank() -> updateResponse = Resource.Failure(errMsg)
                    zipFiles.isNotEmpty() -> {
                        usersUseCase.updateUserUseCase(
                            idUser!!, state.toUser(), zipFiles.first().toUri()
                        ).also { result -> updateResponse = result }
                    }
                }
            }
        } else {
            usersUseCase.updateUserUseCase(idUser!!, state.toUser(), null).also { result ->
                updateResponse = result
            }
        }
    }

    fun onNameInput(input: String) {
        state = state.copy(name = input)
    }

    fun onSurnameInput(input: String) {
        state = state.copy(surname = input)
    }

    fun onPhoneInput(input: String) {
        state = state.copy(phone = input)
    }

    fun onImageInput(uri: Uri) {
        state = state.copy(imgSelected = uri)
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

            idUser.isNullOrBlank() -> {
                errorMessage = ctx.getString(R.string.id_cannot_be_null)
                isValid(false)
            }

            else -> isValid(true)
        }
    }

    fun resetForm() {
        updateResponse = null
        enabledBtn = true
    }
}