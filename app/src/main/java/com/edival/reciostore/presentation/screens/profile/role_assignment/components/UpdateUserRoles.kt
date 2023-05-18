package com.edival.reciostore.presentation.screens.profile.role_assignment.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.profile.role_assignment.ProfileRoleAssignmentViewModel

@Composable
fun UpdateUserRoles(vm: ProfileRoleAssignmentViewModel = hiltViewModel()) {
    when (val response = vm.userResponse) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> {
            vm.clearForm(true)
            Toast.makeText(
                LocalContext.current, R.string.roles_updated_successfully, Toast.LENGTH_SHORT
            ).show()
        }

        is Resource.Failure -> {
            vm.clearForm(false)
            Toast.makeText(LocalContext.current, response.message, Toast.LENGTH_SHORT).show()
        }

        else -> {
            response?.let {
                vm.clearForm(false)
                Toast.makeText(LocalContext.current, R.string.unknown_error, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}