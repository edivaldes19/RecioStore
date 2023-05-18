package com.edival.reciostore.presentation.screens.admin.user.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.edival.reciostore.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdminUserDetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    ViewModel() {
    var user by mutableStateOf<User?>(null)
        private set

    init {
        savedStateHandle.get<String>("user")?.let { userStr ->
            user = User.fromJson(userStr)
            Log.d("AdminUserDetailViewModel", "$user")
        }
    }
}