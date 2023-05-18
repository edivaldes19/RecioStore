package com.edival.reciostore.presentation.screens.admin.user.list

import android.util.Log
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminUserListViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase, private val authUseCase: AuthUseCase
) : ViewModel() {
    var usersResponse by mutableStateOf<Resource<List<User>>?>(null)
        private set

    init {
        viewModelScope.launch {
            usersResponse = Resource.Loading
            authUseCase.getSessionDataUseCase().first().also { data ->
                if (!data.token.isNullOrBlank()) {
                    data.user.also { userNull ->
                        if (userNull != null && !userNull.id.isNullOrBlank()) {
                            usersUseCase.getUsersUseCase(userNull.id).collect { result ->
                                usersResponse = result
                                Log.d("getUsers", "REMOTE: $result")
                            }
                        }
                    }
                }
            }
        }
    }
}