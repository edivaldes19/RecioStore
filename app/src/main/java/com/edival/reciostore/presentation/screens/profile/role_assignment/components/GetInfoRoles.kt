package com.edival.reciostore.presentation.screens.profile.role_assignment.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.profile.role_assignment.ProfileRoleAssignmentViewModel

@Composable
fun GetInfoRoles(padding: PaddingValues, vm: ProfileRoleAssignmentViewModel = hiltViewModel()) {
    when (val response = vm.infoResponse) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> ProfileRoleAssignmentContent(padding, response.data)
        is Resource.Failure -> Toast.makeText(
            LocalContext.current, response.message, Toast.LENGTH_SHORT
        ).show()

        else -> {
            response?.let {
                Toast.makeText(LocalContext.current, R.string.unknown_error, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}