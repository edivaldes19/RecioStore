package com.edival.reciostore.presentation.screens.profile.info

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.useCase.auth.AuthUseCase
import com.edival.reciostore.domain.useCase.users.UsersUseCase
import com.edival.reciostore.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileInfoViewModel @Inject constructor(
    private val authUseCase: AuthUseCase, private val usersUseCase: UsersUseCase
) : ViewModel() {
    var deleteAccountResponse by mutableStateOf<Resource<Unit>?>(null)
        private set
    var downloadUserImgResponse by mutableStateOf<Resource<Unit>?>(null)
        private set
    var user by mutableStateOf<User?>(null)
        private set
    var enabledBtn by mutableStateOf(true)
        private set
    var showMenu by mutableStateOf(false)

    init {
        viewModelScope.launch {
            authUseCase.getSessionDataUseCase().collect { data ->
                if (!data.token.isNullOrBlank()) user = data.user
            }
        }
    }

    fun logOut(): Job = viewModelScope.launch { authUseCase.logOutUseCase() }
    fun deleteAccount(idUser: String): Job = viewModelScope.launch {
        deleteAccountResponse = Resource.Loading
        authUseCase.deleteAccountUseCase(idUser).also { result ->
            deleteAccountResponse = result
        }
    }

    fun downloadUserImg(url: String?, ctx: Context, showMsg: () -> Unit): Job =
        viewModelScope.launch {
            if (!url.isNullOrBlank()) {
                enabledBtn = false
                downloadUserImgResponse = Resource.Loading
                usersUseCase.downloadUserImgUseCase(ctx, url).also { result ->
                    downloadUserImgResponse = result
                }
            } else showMsg()
        }

    fun resetForm() {
        deleteAccountResponse = null
        downloadUserImgResponse = null
        enabledBtn = true
    }
}