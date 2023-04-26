package com.edival.reciostore.presentation.screens.profile.update

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.R
import com.edival.reciostore.core.Config
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.useCase.auth.AuthUseCase
import com.edival.reciostore.domain.useCase.users.UsersUseCase
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.screens.profile.update.mapper.toUser
import com.edival.reciostore.presentation.util.ComposeFileProvider
import com.edival.reciostore.presentation.util.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileUpdateViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val usersUseCase: UsersUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(ProfileUpdateState())
        private set
    var updateResponse by mutableStateOf<Resource<User>?>(null)
        private set
    var errorMessage by mutableStateOf("")
    val resultingActivityHandler = ResultingActivityHandler()
    private var file: File? = null
    private var idUser: String? = null

    init {
        savedStateHandle.get<String>("user")?.let { userStr ->
            User.fromJson(userStr).also { user ->
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

    fun updateUserSession(userResponse: User): Job = viewModelScope.launch {
        authUseCase.updateSessionUseCase(userResponse)
    }

    fun updateUser(): Job = viewModelScope.launch {
        idUser?.let { id ->
            updateResponse = Resource.Loading
            usersUseCase.updateUserUseCase(id, state.toUser()).also { result ->
                updateResponse = result
            }
        }
    }

    fun updateUserImage(): Job = viewModelScope.launch {
        if (idUser != null && file != null) {
            updateResponse = Resource.Loading
            usersUseCase.updateUserImageUseCase(idUser!!, file!!).also { result ->
                updateResponse = result
            }
        }
    }

    fun pickImage(ctx: Context): Job = viewModelScope.launch {
        resultingActivityHandler.getContent(Config.IMAGES_MT).also { result ->
            result?.let { uri ->
                state = state.copy(img = uri.toString())
                file = ComposeFileProvider.createFileFromUri(ctx, uri)
            }
        }
    }

    fun takePhoto(ctx: Context): Job = viewModelScope.launch {
        resultingActivityHandler.takePicturePreview().also { result ->
            result?.let { bitmap ->
                state = state.copy(img = ComposeFileProvider.getPathFromBitmap(ctx, bitmap))
                file = File(state.img!!)
            }
        }
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
        }
        isValid(true)
    }
}