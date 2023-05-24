package com.edival.reciostore.presentation.screens.auth.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.screens.auth.login.LoginViewModel

@Composable
fun CreateToken(vm: LoginViewModel = hiltViewModel(), token: @Composable (String) -> Unit) {
    LaunchedEffect(Unit) { vm.createToken() }
    when (val tokenResponse = vm.tokenResponse) {
        Resource.Loading -> DefaultProgressBar(R.string.getting_notifications_token)
        is Resource.Success -> token(tokenResponse.data)
        is Resource.Failure -> Toast.makeText(
            LocalContext.current, tokenResponse.message, Toast.LENGTH_SHORT
        ).show()

        else -> {
            tokenResponse?.let {
                Toast.makeText(LocalContext.current, R.string.unknown_error, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}