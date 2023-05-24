package com.edival.reciostore.presentation.screens.auth.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.auth.login.LoginViewModel

@Composable
fun UpdateNotificationToken(
    idUser: String?, notifyToken: String, sessionToken: String, vm: LoginViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) { vm.updateNotificationToken(idUser, notifyToken) }
    when (val updateResponse = vm.updateNotTokResponse) {
        Resource.Loading -> DefaultProgressBar(R.string.saving_notification_token)
        is Resource.Success -> {
            LaunchedEffect(Unit) {
                vm.saveSession(AuthResponse(user = updateResponse.data, token = sessionToken))
            }
        }

        is Resource.Failure -> Toast.makeText(
            LocalContext.current, updateResponse.message, Toast.LENGTH_SHORT
        ).show()

        else -> {
            updateResponse?.let {
                Toast.makeText(LocalContext.current, R.string.unknown_error, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}