package com.edival.reciostore.presentation.screens.auth.register.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.reciostore.domain.util.Resource
import com.edival.reciostore.presentation.components.DefaultProgressBar
import com.edival.reciostore.presentation.navigation.screen.AuthScreen
import com.edival.reciostore.presentation.screens.auth.register.RegisterViewModel

@Composable
fun SignUp(navHostController: NavHostController, vm: RegisterViewModel = hiltViewModel()) {
    when (val response = vm.signUpResource) {
        Resource.Loading -> DefaultProgressBar()
        is Resource.Success -> {
            LaunchedEffect(Unit) {
                vm.saveSession(response.data)
                navHostController.navigate(AuthScreen.Home.route)
            }
        }
        is Resource.Failure -> Toast.makeText(
            LocalContext.current, response.message, Toast.LENGTH_SHORT
        ).show()
        else -> {}
    }
}