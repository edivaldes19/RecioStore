package com.edival.reciostore.presentation.screens.auth.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.navigation.Graph
import com.edival.reciostore.presentation.screens.auth.login.LoginViewModel

@Composable
fun Login(navController: NavHostController, vm: LoginViewModel = hiltViewModel()) {
    when (val response = vm.logInResponse) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> {
            LaunchedEffect(Unit) {
                vm.saveSession(response.data)
                response.data.user?.roles?.let { roles ->
                    if (roles.size > 1) {
                        navController.navigate(Graph.ROLES) {
                            popUpTo(Graph.AUTH) { inclusive = true }
                        }
                    } else {
                        roles.first().name?.let { roleName -> vm.saveRoleName(roleName) }
                        navController.navigate(Graph.CLIENT) {
                            popUpTo(Graph.AUTH) { inclusive = true }
                        }
                    }
                }
            }
        }

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