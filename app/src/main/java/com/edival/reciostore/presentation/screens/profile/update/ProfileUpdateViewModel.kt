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
import com.edival.reciostore.domain.useCase.users.UsersUseCase
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.util.ComposeFileProvider
import com.edival.reciostore.presentation.util.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileUpdateViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(ProfileUpdateState())
        private set
    var updateResponse by mutableStateOf<Resource<User>?>(null)
        private set
    var errorMessage by mutableStateOf("")
    val resultingActivityHandler = ResultingActivityHandler()
    private var file: File? = null
    private var idUser: String = ""

    init {
        savedStateHandle.get<String>("user")?.let { userStr ->
            User.fromJson(userStr).also { user ->
                idUser = user.id.orEmpty()
                state = state.copy(
                    name = user.name,
                    surname = user.surname,
                    phone = user.phone,
                    img = user.img.orEmpty()
                )
            }
        }
    }

    fun updateProfile(ctx: Context): Job = viewModelScope.launch {
        if (idUser.isNotBlank()) {
            val userData = User(name = state.name, surname = state.surname, phone = state.phone)
            updateResponse = Resource.Loading
            usersUseCase.updateUserDataUseCase(idUser, userData).also { result ->
                updateResponse = result
            }
        } else errorMessage = ctx.getString(R.string.unknown_id)
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
                file = File(state.img)
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