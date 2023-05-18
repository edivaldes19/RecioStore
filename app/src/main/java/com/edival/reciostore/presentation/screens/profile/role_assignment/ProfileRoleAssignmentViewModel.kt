package com.edival.reciostore.presentation.screens.profile.role_assignment

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.Info
import com.edival.reciostore.domain.model.RoleAssignment
import com.edival.reciostore.domain.model.User
import com.edival.reciostore.domain.useCase.info.InfoUseCase
import com.edival.reciostore.domain.useCase.users.UsersUseCase
import com.edival.reciostore.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileRoleAssignmentViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase,
    private val infoUseCase: InfoUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var roleAssignment by mutableStateOf<RoleAssignment?>(null)
    var state by mutableStateOf(MasterPasswordState())
        private set
    var infoResponse by mutableStateOf<Resource<List<Info>>?>(null)
        private set
    var userResponse by mutableStateOf<Resource<User>?>(null)
        private set
    var enabledBtn by mutableStateOf(true)
        private set
    var errorMessage by mutableStateOf("")

    init {
        savedStateHandle.get<String>("role_assignment")?.let { roleAssignmentStr ->
            roleAssignment = RoleAssignment.fromJson(roleAssignmentStr)
            viewModelScope.launch {
                infoResponse = Resource.Loading
                infoUseCase.getInfoUseCase().collect { result -> infoResponse = result }
            }
        }
    }

    fun getDataToShow(infoList: List<Info>): List<String?> {
        roleAssignment?.let { data ->
            val masterPassword =
                infoList.find { info -> info.key.equals("master_password", true) }?.value
            if (data.toAdmin) {
                val adminQuestion =
                    infoList.find { info -> info.key.equals("admin_question_key", true) }?.value
                val adminFunctions =
                    infoList.find { info -> info.key.equals("admin_functions_key", true) }?.value
                return listOf(adminQuestion, adminFunctions, masterPassword)
            } else {
                val clientQuestion =
                    infoList.find { info -> info.key.equals("client_question_key", true) }?.value
                val clientFunctions =
                    infoList.find { info -> info.key.equals("client_functions_key", true) }?.value
                return listOf(clientQuestion, clientFunctions, masterPassword)
            }
        }
        return listOf()
    }

    fun onUserRoleUpdate(): Job = viewModelScope.launch {
        roleAssignment?.let { data ->
            enabledBtn = false
            userResponse = Resource.Loading
            if (data.toAdmin) {
                usersUseCase.updateUserToAdminUseCase(data.idUser).also { result ->
                    userResponse = result
                }
            } else {
                usersUseCase.updateUserToClientUseCase(data.idUser).also { result ->
                    userResponse = result
                }
            }
        }
    }

    fun onMasterPasswordInput(masterPassword: String) {
        state = state.copy(masterPassword = masterPassword)
    }

    fun validateField(ctx: Context, passwordDB: String, isValid: (Boolean) -> Unit) {
        if (state.masterPassword.isBlank() || state.masterPassword != passwordDB) {
            errorMessage = ctx.getString(R.string.incorrect_password)
            isValid(false)
        } else isValid(true)
    }

    fun clearForm(isOnlyForm: Boolean) {
        if (isOnlyForm) {
            state = state.copy(masterPassword = "")
            userResponse = null
        }
        enabledBtn = true
    }
}