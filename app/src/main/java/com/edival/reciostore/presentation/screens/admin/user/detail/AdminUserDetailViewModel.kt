package com.edival.reciostore.presentation.screens.admin.user.detail

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.useCase.users.UsersUseCase
import com.edival.reciostore.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminUserDetailViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {
    var downloadUserImgResponse by mutableStateOf<Resource<Unit>?>(null)
        private set
    var user by mutableStateOf<User?>(null)
        private set
    var enabledBtn by mutableStateOf(true)
        private set

    init {
        savedStateHandle.get<String>("user")?.let { userStr ->
            user = User.fromJson(userStr)
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
        downloadUserImgResponse = null
        enabledBtn = true
    }
}