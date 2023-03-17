package com.edival.reciostore.presentation.screens.auth.register

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.reciostore.R
import com.edival.reciostore.presentation.components.DefaultTopBar
import com.edival.reciostore.presentation.screens.auth.register.components.RegisterContent

@Composable
fun RegisterScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(
            title = R.string.sign_up, upAvailable = true, navHostController = navHostController
        )
    }) { padding ->
        RegisterContent(
            paddingValues = padding, navHostController = navHostController
        )
    }
}