package com.edival.reciostore.presentation.screens.loading

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.domain.model.Info
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.useCase.auth.AuthUseCase
import com.edival.reciostore.domain.useCase.info.InfoUseCase
import com.edival.reciostore.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadingAppViewModel @Inject constructor(
    private val authUseCase: AuthUseCase, private val infoUseCase: InfoUseCase
) : ViewModel() {
    var infoResponse by mutableStateOf<Resource<Info>?>(null)
        private set
    var user by mutableStateOf<User?>(null)
        private set

    init {
        viewModelScope.launch {
            infoResponse = Resource.Loading
            infoUseCase.getInfoByKeyUseCase("app_version").also { result ->
                infoResponse = result
                authUseCase.getSessionDataUseCase().collect { data ->
                    if (!data.token.isNullOrBlank()) user = data.user
                }
            }
        }
    }

    fun saveRoleName(name: String): Job = viewModelScope.launch {
        authUseCase.saveRoleNameUseCase(name)
    }
}