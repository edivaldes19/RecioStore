package com.edival.reciostore.presentation.screens.auth.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.screens.auth.login.LoginViewModel

@Composable
fun Login(
    navHostController: NavHostController,
    vm: LoginViewModel = hiltViewModel(),
    data: @Composable (AuthResponse) -> Unit
) {
    when (val logInResponse = vm.logInResponse) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> {
            data(logInResponse.data)
            LaunchedEffect(Unit) {
                logInResponse.data.user?.roles?.let { roles ->
                    if (roles.size > 1) {
                        navHostController.navigate(Graph.ROLES) {
                            popUpTo(Graph.AUTH) { inclusive = true }
                        }
                    } else {
                        navHostController.navigate(Graph.CLIENT) {
                            popUpTo(Graph.AUTH) { inclusive = true }
                        }
                    }
                }
            }
        }

        is Resource.Failure -> {
            vm.clearForm()
            Toast.makeText(LocalContext.current, logInResponse.message, Toast.LENGTH_SHORT).show()
        }

        else -> {
            logInResponse?.let {
                vm.clearForm()
                Toast.makeText(LocalContext.current, R.string.unknown_error, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}