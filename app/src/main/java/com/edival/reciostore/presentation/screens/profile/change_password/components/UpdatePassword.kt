package com.edival.reciostore.presentation.screens.profile.change_password.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.profile.change_password.ProfileChangePasswordViewModel

@Composable
fun UpdatePassword(vm: ProfileChangePasswordViewModel = hiltViewModel()) {
    when (val response = vm.changePasswordResponse) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> {
            vm.clearForm(true)
            Toast.makeText(
                LocalContext.current, R.string.password_updated_successfully, Toast.LENGTH_SHORT
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